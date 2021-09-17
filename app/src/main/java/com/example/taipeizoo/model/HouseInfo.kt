package com.example.taipeizoo.model

import com.google.gson.annotations.SerializedName

data class HouseInfo(
        @SerializedName("count") val count: Int,
        @SerializedName("limit") val limit: Int,
        @SerializedName("offset") val offset: Int,
        @SerializedName("results") val results: List<House>,
        @SerializedName("sort") val sort: String
) {
    companion object {
        val defaultInstance = HouseInfo(0, 0, 0, listOf(), "")
    }
}

data class House(
        @SerializedName("E_Category") val category: String,
        @SerializedName("E_Geo") val geo: String,
        @SerializedName("E_Info") val info: String,
        @SerializedName("E_Memo") val memo: String,
        @SerializedName("E_Name") val name: String,
        @SerializedName("E_Pic_URL") private val _picUrl: String,
        @SerializedName("E_URL") val url: String,
        @SerializedName("E_no") val no: String,
        @SerializedName("_id") val id: Int
) {
    val picUrl: String
        get() = _picUrl.replace("http", "https")

}