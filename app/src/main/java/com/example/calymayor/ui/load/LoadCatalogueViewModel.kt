package com.example.calymayor.ui.load

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calymayor.data.db.entities.SelectOptionsEntity
import com.example.calymayor.domain.use_cases.AddSelectionUseCase
import com.example.calymayor.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadCatalogueViewModel @Inject constructor(
    private val addSelectionUseCase: AddSelectionUseCase
) : ViewModel() {

    private val _responseInsertSelection = MutableLiveData<Resource<Boolean>>()
    val responseInsertSelection get() = _responseInsertSelection

    fun insertSelection(selection: SelectOptionsEntity) = viewModelScope.launch {
        addSelectionUseCase.invoke(selection).collect {
            responseInsertSelection.value = it
        }
    }
}