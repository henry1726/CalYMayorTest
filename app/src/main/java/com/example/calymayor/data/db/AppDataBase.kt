package com.example.calymayor.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calymayor.data.db.dao.SelectOptionsDao
import com.example.calymayor.data.db.entities.SelectOptionsEntity

@Database(entities = [SelectOptionsEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun selectOptionsDao(): SelectOptionsDao
}