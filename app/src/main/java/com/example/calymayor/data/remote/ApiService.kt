package com.example.calymayor.data.remote

import com.example.calymayor.data.remote.dto.CatalogueDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/firebase/api/catalogos/Sanit_abastecimiento")
    suspend fun getCatalogue(): Response<CatalogueDTO>
}