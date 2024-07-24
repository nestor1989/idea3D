package com.idea3d.idea3d.data.model.home.news

import android.os.Parcelable
import com.idea3d.idea3d.data.model.home.news.News
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsApiResponse (
    var status:String?=null,
    var code:String?=null,
    var articles:List<News>
): Parcelable