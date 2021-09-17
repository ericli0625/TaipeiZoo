package com.example.taipeizoo.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PlaintInfo(
        @SerializedName("count") val count: Int,
        @SerializedName("limit") val limit: Int,
        @SerializedName("offset") val offset: Int,
        @SerializedName("results") val results: List<Plant>,
        @SerializedName("sort") val sort: String
) {
    companion object {
        val defaultInstance = PlaintInfo(0, 0, 0, listOf(), "")
    }
}

@Parcelize
@Entity(tableName = "plantList")
data class Plant(
        @SerializedName("F_AlsoKnown") val alsoKnown: String,
        @SerializedName("F_Brief") val brief: String,
        @SerializedName("F_CID") val cid: String,
        @SerializedName("F_Code") val code: String,
        @SerializedName("F_Family") val family: String,
        @SerializedName("F_Feature") val feature: String,
        @SerializedName("F_Function＆Application") val usage: String,
        @SerializedName("F_Genus") val genus: String,
        @SerializedName("F_Geo") val geo: String,
        @SerializedName("F_Keywords") val keywords: String,
        @SerializedName("F_Location") val location: String,
        @SerializedName("\uFEFFF_Name_Ch") val nameC: String,
        @SerializedName("F_Name_En") val nameE: String,
        @SerializedName("F_Name_Latin") val nameLatin: String,
        @SerializedName("F_Pic01_ALT") val pic01Alt: String,
        @SerializedName("F_Pic01_URL") private val _pic01Url: String,
        @SerializedName("F_Pic02_ALT") val pic02Alt: String,
        @SerializedName("F_Pic02_URL") val pic02Url: String,
        @SerializedName("F_Pic03_ALT") val pic03Alt: String,
        @SerializedName("F_Pic03_URL") val pic03Url: String,
        @SerializedName("F_Pic04_ALT") val pic04Alt: String,
        @SerializedName("F_Pic04_URL") val pic04Url: String,
        @SerializedName("F_Summary") val summary: String,
        @SerializedName("F_Update") val updateDate: String,
        @SerializedName("F_Vedio_URL") val videoUrl: String,
        @SerializedName("F_Voice01_ALT") val voice01Alt: String,
        @SerializedName("F_Voice01_URL") val voice01Url: String,
        @SerializedName("F_Voice02_ALT") val voice02Alt: String,
        @SerializedName("F_Voice02_URL") val voice02Url: String,
        @SerializedName("F_Voice03_ALT") val voice03Alt: String,
        @SerializedName("F_Voice03_URL") val voice03Url: String,
        @SerializedName("F_pdf01_ALT") val pdf01Alt: String,
        @SerializedName("F_pdf01_URL") val pdf01Url: String,
        @SerializedName("F_pdf02_ALT") val pdf02Alt: String,
        @SerializedName("F_pdf02_URL") val pdf02Url: String,
        @PrimaryKey @ColumnInfo(name = "id") @SerializedName("_id") val id: Int
) : Parcelable {
    val pic01Url: String
        get() = if (_pic01Url.contains("https://")) {
            _pic01Url
        } else {
            _pic01Url.replace("http", "https")
        }

    companion object {
        val defaultInstance = Plant("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 0)
    }
}