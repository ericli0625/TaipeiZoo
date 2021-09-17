package com.example.taipeizoo.model

import com.google.gson.annotations.SerializedName

data class PlaintInfo(
        @SerializedName("count") val count: Int,
        @SerializedName("limit") val limit: Int,
        @SerializedName("offset") val offset: Int,
        @SerializedName("results") val results: List<Plant>,
        @SerializedName("sort") val sort: String
)

data class Plant(
        @SerializedName("F_AlsoKnown") val F_AlsoKnown: String,
        @SerializedName("F_Brief") val brief: String,
        @SerializedName("F_CID") val cid: String,
        @SerializedName("F_Code") val code: String,
        @SerializedName("F_Family") val family: String,
        @SerializedName("F_Feature") val feature: String,
        @SerializedName("F_Function&Application") val usage: String,
        @SerializedName("F_Genus") val genus: String,
        @SerializedName("F_Geo") val geo: String,
        @SerializedName("F_Keywords") val keywords: String,
        @SerializedName("F_Location") val location: String,
        @SerializedName("F_Name_Ch") val nameC: String,
        @SerializedName("F_Name_En") val nameE: String,
        @SerializedName("F_Name_Latin") val nameLatin: String,
        @SerializedName("F_Pic01_ALT") val F_Pic01Alt: String,
        @SerializedName("F_Pic01_URL") val F_Pic01Url: String,
        @SerializedName("F_Pic02_ALT") val F_Pic02Alt: String,
        @SerializedName("F_Pic02_URL") val F_Pic02Url: String,
        @SerializedName("F_Pic03_ALT") val F_Pic03Alt: String,
        @SerializedName("F_Pic03_URL") val F_Pic03Url: String,
        @SerializedName("F_Pic04_ALT") val F_Pic04Alt: String,
        @SerializedName("F_Pic04_URL") val F_Pic04Url: String,
        @SerializedName("F_Summary") val summary: String,
        @SerializedName("F_Update") val update: String,
        @SerializedName("F_Vedio_URL") val videoUrl: String,
        @SerializedName("F_Voice01_ALT") val voice01Alt: String,
        @SerializedName("F_Voice01_URL") val voice01Url: String,
        @SerializedName("F_Voice02_ALT") val voice02Alt: String,
        @SerializedName("F_Voice02_URL") val voice02Url: String,
        @SerializedName("F_Voice03_ALT") val voice03Alt: String,
        @SerializedName("F_Voice03_URL") val voice03Url: String,
        @SerializedName("F_pdf01_ALT") val F_pdf01Alt: String,
        @SerializedName("F_pdf01_URL") val F_pdf01Url: String,
        @SerializedName("F_pdf02_ALT") val F_pdf02Alt: String,
        @SerializedName("F_pdf02_URL") val F_pdf02Url: String,
        @SerializedName("_id") val id: Int
) {
    companion object {
        val defaultInstance = Plant("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 0)
    }
}