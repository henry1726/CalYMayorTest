package com.example.calymayor.ui.download

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calymayor.databinding.ActivityDownloadCatalogueBinding
import com.example.calymayor.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DownloadCatalogueActivity : AppCompatActivity() {

    private val binding: ActivityDownloadCatalogueBinding by lazy {
        ActivityDownloadCatalogueBinding.inflate(layoutInflater)
    }
    private val viewModel: DownloadCatalogueViewModel by viewModels()
    private lateinit var adapter: CatalogueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUi()
        initObservers()
    }

    private fun initUi() {
        with(binding){
            viewModel.getCatalogue()
            rvDownload.layoutManager = LinearLayoutManager(this@DownloadCatalogueActivity)
        }
    }

    private fun initObservers() {
        initObserverGetCatalogue()
    }

    private fun initObserverGetCatalogue() {
        viewModel.responseGetCatalogue.observe(this@DownloadCatalogueActivity){ catalogues->
            catalogues.data.let {result ->
                when(catalogues.status){
                    Resource.Status.SUCCESS -> {
                        result?.let {
                            binding.progress.visibility = View.GONE
                            binding.rvDownload.visibility = View.VISIBLE
                            adapter = CatalogueAdapter(it.Sanit_abastecimiento, this@DownloadCatalogueActivity)
                            binding.rvDownload.adapter = adapter
                        }
                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(this@DownloadCatalogueActivity, catalogues.message, Toast.LENGTH_SHORT).show()
                    }
                    Resource.Status.LOADING ->{
                        binding.progress.visibility = View.VISIBLE
                        binding.rvDownload.visibility = View.GONE
                    }
                }
            }
        }
    }
}