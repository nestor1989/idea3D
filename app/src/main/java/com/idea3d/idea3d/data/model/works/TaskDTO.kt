package com.idea3d.idea3d.data.model.works

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskDTO(
    val id: Long? = null,
    var name: String,
    var description: String,
    var id_status: Int? = null,
    var status: String? = null,
    var prioritize: Boolean = false,
    var client: String? = null,
    var id_client: Int? = null,
    var price: Float?,
    var cost: Float?,
    var thing_photo: String?=null,
    var thing_extension: String?=null,
    var client_photo: String?=null,
    var client_ext:String? = null,
    var date_begin: String? = null,
    var date_end: String? = null
): Parcelable