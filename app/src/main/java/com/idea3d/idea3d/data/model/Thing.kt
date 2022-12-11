package com.idea3d.idea3d.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thing(
    @SerializedName ("id")
    var id: Int,
    @SerializedName("name")
    var name:String,
    @SerializedName("thumbnail")
    var image:String,
    @SerializedName("public_url")
    val url:String
    ):Parcelable

@Parcelize
data class Things(
    @SerializedName("hits")
    val thingsList: List<Thing>,
    @SerializedName("total")
    val totalThings: Int
): Parcelable

@Entity
data class ThingEntity(
    @PrimaryKey
    var id: Int,
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "thumbnail")
    var image:String,
    @ColumnInfo(name = "public_url")
    val url:String
)


