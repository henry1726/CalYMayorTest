package com.example.calymayor.ui.load

import android.content.pm.PackageManager
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.calymayor.R
import com.example.calymayor.data.db.entities.SelectOptionsEntity
import com.example.calymayor.databinding.ActivityLoadCatologueBinding
import com.example.calymayor.utils.Constants
import com.example.calymayor.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class LoadCatalogueActivity : AppCompatActivity() {

    private val binding: ActivityLoadCatologueBinding by lazy {
        ActivityLoadCatologueBinding.inflate(layoutInflater)
    }

    private val viewModel: LoadCatalogueViewModel by viewModels()
    private lateinit var adapter: LoadCatalogueAdapter
    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        outputDirectory = getOutputDirectory()
        initUi()
        initObservers()
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let { mFile ->
            File(mFile, resources.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }

        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    private fun initObservers() {
        initObserverInsertSelection()
    }

    private fun initObserverInsertSelection() {
        viewModel.responseInsertSelection.observe(this@LoadCatalogueActivity){ res ->
            res.data.let {
                when(res.status){
                    Resource.Status.SUCCESS -> Toast.makeText(this@LoadCatalogueActivity, getString(R.string.save_selection), Toast.LENGTH_SHORT).show()
                    Resource.Status.ERROR -> Toast.makeText(this@LoadCatalogueActivity, res.message, Toast.LENGTH_SHORT).show()
                    Resource.Status.LOADING -> {}
                }
            }
        }
    }

    private fun initUi() {
        with(binding){
            rvLoad.layoutManager = LinearLayoutManager(this@LoadCatalogueActivity)
            adapter = LoadCatalogueAdapter(
                Constants.listSelections, this@LoadCatalogueActivity
            ){ item, listener ->
                when(listener){
                    ActionListener.CLICK_CHECKBOX_YES -> {
                        viewModel.insertSelection(SelectOptionsEntity(
                            item.idProvision, Constants.OPTION_YES, ""
                        ))
                    }
                    ActionListener.CLICK_CHECKBOX_NO -> {
                        viewModel.insertSelection(SelectOptionsEntity(
                            item.idProvision, Constants.OPTION_NO, ""
                        ))
                    }
                    ActionListener.CLICK_CHECKBOX_NA -> {
                        viewModel.insertSelection(SelectOptionsEntity(
                            item.idProvision, Constants.OPTION_NA, ""
                        ))
                    }
                }
            }
            rvLoad.adapter = adapter
            imgTakePhoto.setOnClickListener {
                checkPermission()
            }
            button.setOnClickListener {
                takePhoto()
            }
        }
    }

    private fun checkPermission(){
        if(allPermissionGranted()){
            startCamera()
        }else{
            ActivityCompat.requestPermissions(
                this@LoadCatalogueActivity, Constants.REQUIRED_PERMISSIONS, Constants.REQUEST_CODE_PERMISSIONS
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.REQUEST_CODE_PERMISSIONS){
            if(allPermissionGranted()){
                startCamera()
            }else{
                Toast.makeText(this@LoadCatalogueActivity, getString(R.string.no_permission), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startCamera() {
        binding.clFront.visibility = View.GONE
        binding.clCamera.visibility = View.VISIBLE
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this@LoadCatalogueActivity)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also { mPreview ->
                    mPreview.setSurfaceProvider(
                        binding.viewFinder.surfaceProvider
                    )
                }
            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this@LoadCatalogueActivity, cameraSelector, preview, imageCapture
                )
            }catch (e: Exception){
                Log.d(Constants.TAG, "startCamera Fail", e)
            }
        }, ContextCompat.getMainExecutor(this@LoadCatalogueActivity))

    }

    private fun takePhoto(){
        val imageCapture = imageCapture ?: return
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(Constants.FILE_NAME_FORMAT, Locale.getDefault()).format(System.currentTimeMillis()) + ".jpg")
        val outputOption = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOption, ContextCompat.getMainExecutor(this@LoadCatalogueActivity),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    Glide.with(this@LoadCatalogueActivity)
                        .load(savedUri)
                        .centerCrop()
                        .into(binding.imgTakePhoto)
                    binding.clFront.visibility = View.VISIBLE
                    binding.clCamera.visibility = View.GONE
                    Toast.makeText(this@LoadCatalogueActivity, "saved photo: $savedUri", Toast.LENGTH_SHORT).show()
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e(Constants.TAG, "onError: ${exception.message}", exception)
                }

            }
        )


    }

    private fun allPermissionGranted() =
         Constants.REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                baseContext, it
            ) == PackageManager.PERMISSION_GRANTED
        }
}