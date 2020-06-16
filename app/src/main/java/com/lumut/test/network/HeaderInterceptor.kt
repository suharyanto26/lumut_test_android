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
        val url = chain.request().url()
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
//                        val token = getNewToken()
                        try {
                            val requestBuilder = chain.request().newBuilder()
//                                .addHeader("Authorization", "BEARER $token")
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

    /*private fun getNewToken(): String {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val map = HashMap<String, String>()
        map["access_token"] = PreferencesUtil.getToken(context)
        map["refresh_token"] = PreferencesUtil.getRefreshToken(context)
        val reqbody = RequestBody.create(null, Gson().toJson(map))
        val req = Request.Builder()
            .url( "jajajaja")
            .method("POST", reqbody).build()
        val response = client.newCall(req).execute()
        var newToken = PreferencesUtil.getToken(context)
        if (response.code() == 200) {
            response.body()?.apply {
                val jsonBody = this.string()
                val jsonObj = JSONObject(jsonBody)
                if (jsonObj.has("data")) {
                    val data = jsonObj.getJSONObject("data")
                    if (data.has("access_token")) {
                        val getNewToken = data.getString("access_token")
                        if (!TextUtils.isEmpty(getNewToken)) {
                            newToken = getNewToken
                            PreferencesUtil.setToken(context, newToken)
                        }
                    }
                }
            }
        } else  {
            response.body()?.apply {
                val jsonObj = JSONObject(this.string())
                val model =  Gson().fromJson<Error500Response>(jsonObj.toString(), object : TypeToken<Error500Response>() {}.type)

                if(model.errors!![0].internalMessage.contains(other = "signature is invalid")) {
                    // trigger to BaseActivity show popoup force logout
                }
            }
        }
        return newToken
    }*/

    private fun bodyToString(request: RequestBody?): String {
        try {
            val buffer = Buffer()
            if (request != null)
                request.writeTo(buffer)
            else
                return ""
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "did not work"
        }

    }

}