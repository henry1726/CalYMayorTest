package com.example.calymayor.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calymayor.R
import com.example.calymayor.databinding.ActivityMainBinding
import com.example.calymayor.ui.download.DownloadCatalogueActivity
import com.example.calymayor.ui.load.LoadCatalogueActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        with(binding){
            btnGoToDownload.setOnClickListener {
                startActivity(Intent(this@MainActivity, DownloadCatalogueActivity::class.java))
            }

            btnGoToConsult.setOnClickListener {
                startActivity(Intent(this@MainActivity, LoadCatalogueActivity::class.java))
            }
        }
    }
}