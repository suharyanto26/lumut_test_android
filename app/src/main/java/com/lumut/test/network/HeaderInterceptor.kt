package com.lumut.test.network

import android.content.Context
import com.lumut.test.util.PreferencesUtil
import okhttp3.*
import okio.Buffer
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class HeaderInterceptor constructor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        try {
            val requestBuilder = request.newBuilder()
                .addHeader("Authorization", "BEARER " + PreferencesUtil.getToken(context))
                .addHeader("deviceplatform", "android")
            request = requestBuilder.build()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        var response = chain.proceed(request)
        val code = response.code()
        var stringData = ""
        response.body()?.string()?.let { json ->
            stringData = json
            try {
                val obj = JSONObject(json)
                if (obj.has("status")) {
                    val status = obj.getInt("status")
                    if (status == 401 || status == 402) {
                        try {
                            val requestBuilder = chain.request().newBuilder()
                                .addHeader("deviceplatform", "android")
                            request = requestBuilder.build()
                            response = chain.proceed(request)
                            response.body()?.string()?.let { json ->
                                stringData = json
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        val contentType = response.body()?.contentType()
        val body = ResponseBody.create(contentType, stringData)
        return response.newBuilder().code(if (code >= 500) code else 200).body(body).build()
    }

}