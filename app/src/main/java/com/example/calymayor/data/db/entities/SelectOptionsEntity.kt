package com.example.calymayor.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "select_options")
data class SelectOptionsEntity(
    @PrimaryKey val idProvision: Int,
    val optionSelect: Int,
    val photo: String
)
