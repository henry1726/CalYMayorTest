package com.example.calymayor.domain.use_cases

import com.example.calymayor.data.db.entities.SelectOptionsEntity
import com.example.calymayor.domain.repository.CatalogueLocalRepository
import com.example.calymayor.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddSelectionUseCase @Inject constructor(
    private val repository: CatalogueLocalRepository
){
    suspend operator fun invoke(selectOptionsEntity: SelectOptionsEntity) : Flow<Resource<Boolean>> = flow {
        emit(Resource.loading(null))
        val list = repository.getAllSelections()
        if (list.contains(selectOptionsEntity).not())
            repository.insertSelection(selectOptionsEntity)
        else
            repository.updateSelection(id = selectOptionsEntity.idProvision, selectOption = selectOptionsEntity.optionSelect)
        emit(Resource.success(true))
    }
}