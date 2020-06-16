package com.lumut.test.network

import com.google.gson.Gson
import com.lumut.test.model.TodosModel
import io.reactivex.Flowable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface JsonPlaceholderApi {
    @GET("todos")
    fun getTodos(): Flowable<ArrayList<TodosModel>>

    @GET("todos/{id}")
    fun getDetailTodo(@Path("id") id: Int): Flowable<TodosModel>

    class Creator {
        @Inject
        fun newJsonPlaceholderApi(url: String, httpClient: OkHttpClient, gson: Gson): JsonPlaceholderApi {
            val retrofit = Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()

            return retrofit.create(JsonPlaceholderApi::class.java)
        }
    }
}