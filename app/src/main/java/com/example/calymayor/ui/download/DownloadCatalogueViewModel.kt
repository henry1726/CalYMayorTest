package com.example.calymayor.ui.download

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calymayor.data.remote.dto.CatalogueDTO
import com.example.calymayor.domain.use_cases.GetCatalogueUseCase
import com.example.calymayor.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadCatalogueViewModel @Inject constructor(
    private val getCatalogueUseCase: GetCatalogueUseCase
) : ViewModel(){

    private val _responseGetCatalogue = MutableLiveData<Resource<CatalogueDTO?>>()
    val responseGetCatalogue get() = _responseGetCatalogue

    fun getCatalogue() = viewModelScope.launch {
        getCatalogueUseCase.invoke().collect{
            responseGetCatalogue.value = it
        }
    }
}