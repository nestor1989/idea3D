package com.idea3d.idea3d.data.model.info

import androidx.annotation.DrawableRes


data class Info(

    @DrawableRes val imagenRes: Int,
    val titulo:String,
    val descripcion: String
)
