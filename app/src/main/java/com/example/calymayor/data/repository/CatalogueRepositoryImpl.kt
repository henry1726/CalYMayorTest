package com.example.calymayor.data.repository


import com.example.calymayor.data.remote.ApiService
import com.example.calymayor.data.remote.dto.CatalogueDTO
import com.example.calymayor.domain.repository.CatalogueRepository
import com.example.calymayor.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatalogueRepositoryImpl @Inject constructor(
    private val api: ApiService
): CatalogueRepository {
    override suspend fun getCatalogue(): Flow<Resource<CatalogueDTO?>> = flow {
        val result = api.getCatalogue().run {
            if (isSuccessful)
                Resource.success(body())
            else
                Resource.error(errorBody().toString())
        }
        emit(result)
    }


}