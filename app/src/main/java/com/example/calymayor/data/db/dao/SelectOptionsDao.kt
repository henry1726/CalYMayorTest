package com.example.calymayor.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.calymayor.data.db.entities.SelectOptionsEntity


@Dao
interface SelectOptionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSelection(selection: SelectOptionsEntity)

    @Query("SELECT * FROM select_options")
    suspend fun getAllSelectOptions(): List<SelectOptionsEntity>

    @Query("UPDATE select_options SET optionSelect = :selectOption WHERE idProvision = :id")
    suspend fun updateSelectOption(id: Int, selectOption: Int)

    @Query("DELETE FROM select_options")
    suspend fun deleteFavorite()
}