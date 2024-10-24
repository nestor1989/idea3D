package com.idea3d.idea3d.utils

import android.app.Activity
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import android.util.Base64
import android.webkit.MimeTypeMap
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Functional {
    companion object{

        fun displayName(activity: Activity, uri: Uri?): String? {
            val mCursor: Cursor? =
                activity.applicationContext.contentResolver.query(uri!!, null, null, null, null)

            val indexedname: Int = mCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            mCursor.moveToFirst()
            val filename: String = mCursor!!.getString(indexedname)
            mCursor.close()

            return filename

        }

        fun getBase64ScaledImageString(bitmap: Bitmap): String? {
            val baos = ByteArrayOutputStream()
            val nh = (bitmap.height * (512.0 / bitmap.width)).toInt()
            val scaled = Bitmap.createScaledBitmap(bitmap, 500, nh, true)
            scaled.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val imageBytes = baos.toByteArray()
            val base64String = Base64.encodeToString(imageBytes, Base64.NO_WRAP)
            return base64String.trim { it <= ' ' }
        }

        fun getExtensionFromFile(context: Activity, uri: Uri?): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val cR = context.contentResolver
                val mime = MimeTypeMap.getSingleton()
                "." + mime.getExtensionFromMimeType(cR.getType(uri!!))
            } else {
                ".jpg"
            }
        }

        val ACCEPT_MIME_TYPES = arrayOf(
            "application/pdf",
            "image/*"
        )

        @RequiresApi(Build.VERSION_CODES.O)
        fun convertDatesToDisplay(dateApi: String): String {
            val inputFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
            val outputFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH)
            val date: LocalDate = LocalDate.parse(dateApi, inputFormatter)
            return outputFormatter.format(date)
        }

        fun convertDatesToSQL(date: String): String {
            // Array with possible input formats
            val inputFormats = arrayOf("dd/MM/yyyy", "d/M/yyyy", "dd/M/yyyy", "d/MM/yyyy")

            // Desired output format
            val outputFormat = SimpleDateFormat("yyyy-MM-dd")

            // Try to parse the date with each format
            for (format in inputFormats) {
                try {
                    val formattedDate = SimpleDateFormat(format).parse(date)
                    return outputFormat.format(formattedDate)
                } catch (e: Exception) {
                    // The date doesn't match the current format, try the next format
                }
            }

            // If the date couldn't be converted, return the original date
            return date
        }



    }

}