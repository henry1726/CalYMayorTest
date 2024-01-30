package com.example.calymayor.utils


import android.Manifest
import android.os.Build
import com.example.calymayor.R
import com.example.calymayor.data.db.entities.SelectOptionsEntity

object Constants {
    const val BASE_URL = "https://www.calymayor.com.mx/"
    const val NAME_DATABASE = "app_database"
    const val OPTION_YES = 1
    const val OPTION_NO = 2
    const val OPTION_NA = 3
    val listSelections = listOf(
        SelectOptionsEntity(
            1, 0, ""
        ),
        SelectOptionsEntity(
            2, 0, ""
        ),
        SelectOptionsEntity(
            3, 0, ""
        ),
        SelectOptionsEntity(
            4, 0, ""
        ),
        SelectOptionsEntity(
            5, 0, ""
        )
    )
    const val TAG = "cameraX"
    const val FILE_NAME_FORMAT = "yy-MM-dd-HH-mm-ss-SSS"
    const val REQUEST_CODE_PERMISSIONS = 123
    val REQUIRED_PERMISSIONS =
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
        arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_MEDIA_IMAGES
        )
    }else{
        arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }
}