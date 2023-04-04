package com.idea3d.idea3d.data.model

data class Task(
    val id: Int,
    var name: String,
    var id_status: Int,
    var status: String,
    var prioritize: Boolean = false,
    var client: String,
    var id_client: Int,
    var price: Float,
    var cost: Float,
    var thing_photo: String?=null,
    var thing_extension: String?=null,
    var client_photo: String?=null,
    var client_ext:String? = null,
    var date_begin: String,
    var date_end: String
    )
