package com.example.calymayor.domain.use_cases

import com.example.calymayor.data.remote.dto.CatalogueDTO
import com.example.calymayor.domain.repository.CatalogueRepository
import com.example.calymayor.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatalogueUseCase @Inject constructor(
    private val repository: CatalogueRepository
) {
    suspend operator fun invoke(): Flow<Resource<CatalogueDTO?>> = repository.getCatalogue()
}