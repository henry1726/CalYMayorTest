package com.example.calymayor.domain.repository


import com.example.calymayor.data.remote.dto.CatalogueDTO
import com.example.calymayor.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CatalogueRepository {
    suspend fun getCatalogue(): Flow<Resource<CatalogueDTO?>>
}