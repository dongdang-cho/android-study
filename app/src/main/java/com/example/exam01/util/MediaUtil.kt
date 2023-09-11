package com.example.exam01.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import java.io.OutputStream
import java.lang.Exception


private const val IMAGE_JPEG_SUFFIX = ".jpg"
private const val IMAGE_MIME_TYPE = "image/jpeg"

class MediaUtil {

    companion object {

        fun Bitmap.saveToGallery(context: Context): Uri? {

            val imageOutputStream: OutputStream

            try {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                    val resolver = context.contentResolver
                    val contentValues = ContentValues()

                    contentValues.apply {
                        put(
                            MediaStore.MediaColumns.DISPLAY_NAME,
                            "${System.currentTimeMillis()}$IMAGE_JPEG_SUFFIX"
                        )
                        put(MediaStore.MediaColumns.MIME_TYPE, IMAGE_MIME_TYPE)
                        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                    }

                    val imageUri =
                        resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                    imageOutputStream = resolver.openOutputStream(imageUri!!)!!

                    imageOutputStream.use {
                        this.compress(Bitmap.CompressFormat.JPEG, 100, it)
                    }

                    Toast.makeText(context, "Image saved to gallery", Toast.LENGTH_SHORT).show()

                    return imageUri
                } else {
                    val imageUrl = MediaStore.Images.Media.insertImage(
                        context.contentResolver,
                        this,
                        "${System.currentTimeMillis()}",
                        "${context.applicationInfo.loadLabel(context.packageManager)}-image"
                    )

                    val savedImageUri = Uri.parse(imageUrl)

                    Toast.makeText(context, "Image saved to gallery", Toast.LENGTH_SHORT).show()


                    return savedImageUri
                }

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Image not saved \n$e", Toast.LENGTH_SHORT).show()
            }

            return null
        }

    }
}