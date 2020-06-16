package com.lumut.test.injector.module

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.lumut.test.injector.annotation.ApplicationContext
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lumut.test.BuildConfig
import com.lumut.test.network.HeaderInterceptor
import com.lumut.test.network.JsonPlaceholderApi
import com.lumut.test.network.ServerBusyInterceptor
import com.lumut.test.network.TLSSocketFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.*

@Module
class ApplicationModule(application: Application) {
    private val _app: Application = application

    @Provides
    internal fun providesApplication(): Application {
        return _app
    }

    @Provides
    @ApplicationContext
    internal fun providesContext(): Context {
        return _app
    }

    @Provides
    @Singleton
    internal fun providesGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun providesClient(@ApplicationContext context: Context, cache: Cache): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        val tlsSocketFactory = TLSSocketFactory()

        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager{
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                    return arrayOf()
                }
            }
        )

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory

        return OkHttpClient.Builder()
            .cache(cache)
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true }
            .addInterceptor(interceptor)
            .addInterceptor(HeaderInterceptor(context))
            .addInterceptor(ServerBusyInterceptor(context))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    internal fun providesCache(@ApplicationContext context: Context): Cache {
        val cacheSize = (5 * 1024 * 1024).toLong()
        return Cache(context.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    internal fun provideInterfaceEndPointApi(httpClient: OkHttpClient, gson: Gson): JsonPlaceholderApi {
        return JsonPlaceholderApi.Creator().newJsonPlaceholderApi(BuildConfig.ENDPOINT_API,httpClient, gson)
    }
}