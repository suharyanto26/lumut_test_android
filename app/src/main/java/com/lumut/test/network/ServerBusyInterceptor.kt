package com.lumut.test.network

import android.content.Context
import com.lumut.test.util.buttomsheet.ServerBusySheetDialog
import com.lumut.test.injector.RxBus
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit

class ServerBusyInterceptor(val context: Context) : Interceptor {

    private var retrofit: Retrofit? = null
    private var busyDialog: ServerBusySheetDialog = ServerBusySheetDialog()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        when {
            response.code() >= 501 -> {
                RxBus.publish(busyDialog.showDialog(context))
            }

        }
        return response
    }

    private inline fun <reified T> getError(body: ResponseBody?): T? = body?.let {
        retrofit?.responseBodyConverter<T>(T::class.java, T::class.java.annotations)?.convert(it)
    }
}