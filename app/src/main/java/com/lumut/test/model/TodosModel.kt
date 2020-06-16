package com.lumut.test.model

import com.google.gson.annotations.SerializedName

data class TodosModel(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("userId") val userId: Int = 0,
    @SerializedName("title") val title: String = "",
    @SerializedName("completed") val completed: Boolean
)