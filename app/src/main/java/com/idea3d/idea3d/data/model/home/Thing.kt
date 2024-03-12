package com.idea3d.idea3d.data.model.home

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Things(
    @SerializedName("hits")
    val thingsList: List<ThingEntity>,
    @SerializedName("total")
    val totalThings: Int
): Parcelable

@Parcelize
data class ThingsDTO(
    @SerializedName("hits")
    val thingsList: List<ThingDTO>,
    @SerializedName("total")
    val totalThings: Int
): Parcelable

@Entity
@Parcelize
data class ThingEntity(
    @PrimaryKey(autoGenerate = true)
    var entityKey: Long? = null,
    @ColumnInfo(name = "id")
    var id: Long,
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "thumbnail")
    @SerializedName("thumbnail")
    var image:String,
    @ColumnInfo(name = "public_url")
    @SerializedName("public_url")
    val url:String,
    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
):Parcelable

@Parcelize
data class ThingWithCat(
    var things: List<ThingEntity>,
    var catId: Int,
    var catName: String
): Parcelable

@Parcelize
data class ThingDTO(
    var entityKey: Long? = null,
    var id: Long,
    var name:String,
    var image:String,
    val url:String,
    var favorite: Boolean
):Parcelable

@Parcelize
data class ThingWithCatDTO(
    var things: List<ThingDTO>,
    var catId: Int,
    var catName: String
): Parcelable

