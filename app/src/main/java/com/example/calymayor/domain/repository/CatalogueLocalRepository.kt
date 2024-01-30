package com.example.calymayor.domain.repository

import com.example.calymayor.data.db.entities.SelectOptionsEntity

interface CatalogueLocalRepository {
    suspend fun insertSelection(selectOptionsEntity: SelectOptionsEntity)
    suspend fun getAllSelections(): List<SelectOptionsEntity>
    suspend fun updateSelection(id: Int, selectOption: Int)
}