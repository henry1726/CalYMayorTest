package com.example.calymayor.data.remote.dto

data class SanitAbastecimiento(
    val fechaCreacion: String,
    val fechaEliminacion: String,
    val fechaModificacion: String,
    val idAbastecimiento: Int,
    val tipoAbastecimiento: String,
    val usuarioCreacion: String,
    val usuarioEliminacion: String,
    val usuarioModificacion: String
)