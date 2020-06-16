package com.lumut.test.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Error500Response(
    @SerializedName(value = "status")
    @Expose
    var status: Int = 0,
    @SerializedName(value = "message")
    @Expose
    var message: Message? = null,
    @SerializedName(value = "errors")
    @Expose
    var errors: MutableList<Errors>? = mutableListOf()

) {
    data class Message(
        @SerializedName("en")
        val en: String,
        @SerializedName("id")
        val id: String
    )

    data class Errors(
        @SerializedName(value = "userMessage")
        @Expose
        var userMessage: String = "",
        @SerializedName(value = "internalMessage")
        @Expose
        var internalMessage: String = "",
        @SerializedName(value = "code")
        @Expose
        var code: Int? = 0,
        @SerializedName(value = "moreInfo")
        @Expose
        var moreInfo: String = ""
    )
}