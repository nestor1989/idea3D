package com.idea3d.idea3d.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsApiResponse (
    var status:String?=null,
    var code:String?=null,
    var articles:List<News>
): Parcelable