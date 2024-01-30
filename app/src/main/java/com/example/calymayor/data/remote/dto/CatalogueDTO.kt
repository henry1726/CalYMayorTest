package com.example.calymayor.data.remote.dto

data class CatalogueDTO(
    val Sanit_abastecimiento: List<SanitAbastecimiento>,
    val message: String,
    val success: Int
)