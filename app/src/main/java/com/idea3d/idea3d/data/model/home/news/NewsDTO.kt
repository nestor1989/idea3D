package com.idea3d.idea3d.data.model.home.news

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsDTO (
    var title:String,
    var content: String?,
    var author:String?,
    var url:String,
    var urlToImage:String
): Parcelable