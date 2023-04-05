package com.idea3d.idea3d.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey
    val id: Int,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var id_status: Int,
    @ColumnInfo
    var status: String,
    @ColumnInfo
    var prioritize: Boolean = false,
    @ColumnInfo
    var client: String,
    @ColumnInfo
    var id_client: Int,
    @ColumnInfo
    var price: Float,
    @ColumnInfo
    var cost: Float,
    @ColumnInfo
    var thing_photo: String?=null,
    @ColumnInfo
    var thing_extension: String?=null,
    @ColumnInfo
    var client_photo: String?=null,
    @ColumnInfo
    var client_ext:String? = null,
    @ColumnInfo
    var date_begin: String,
    @ColumnInfo
    var date_end: String
    )
