package com.idea3d.idea3d.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thing(
    @SerializedName ("id")
    var id: Int,
    @SerializedName("name")
    var name:String
):Parcelable

@Parcelize
data class Things(
    @SerializedName("hits")
    val thingsList: List<Thing>
): Parcelable
