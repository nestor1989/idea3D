package com.idea3d.idea3d.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var id_status: Int? = null,
    @ColumnInfo
    var status: String? = null,
    @ColumnInfo
    var prioritize: Boolean = false,
    @ColumnInfo
    var client: String? = null,
    @ColumnInfo
    var id_client: Int? = null,
    @ColumnInfo
    var price: Float?,
    @ColumnInfo
    var cost: Float?,
    @ColumnInfo
    var thing_photo: String?=null,
    @ColumnInfo
    var thing_extension: String?=null,
    @ColumnInfo
    var client_photo: String?=null,
    @ColumnInfo
    var client_ext:String? = null,
    @ColumnInfo
    var date_begin: String? = null,
    @ColumnInfo
    var date_end: String? = null
    )
