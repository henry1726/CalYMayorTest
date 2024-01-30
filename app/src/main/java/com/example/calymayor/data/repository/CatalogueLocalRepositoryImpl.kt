package com.example.calymayor.data.repository

import com.example.calymayor.data.db.dao.SelectOptionsDao
import com.example.calymayor.data.db.entities.SelectOptionsEntity
import com.example.calymayor.domain.repository.CatalogueLocalRepository
import javax.inject.Inject

class CatalogueLocalRepositoryImpl @Inject constructor(
    private val dao: SelectOptionsDao
): CatalogueLocalRepository{

    override suspend fun insertSelection(selectOptionsEntity: SelectOptionsEntity) {
        dao.insertSelection(selectOptionsEntity)
    }

    override suspend fun getAllSelections(): List<SelectOptionsEntity> {
        return dao.getAllSelectOptions()
    }

    override suspend fun updateSelection(id: Int, selectOption: Int) {
        dao.updateSelectOption(id, selectOption)
    }
}