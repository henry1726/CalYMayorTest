package com.example.calymayor.di

import android.content.Context
import androidx.room.Room
import com.example.calymayor.data.db.AppDataBase
import com.example.calymayor.data.remote.ApiService
import com.example.calymayor.data.repository.CatalogueLocalRepositoryImpl
import com.example.calymayor.data.repository.CatalogueRepositoryImpl
import com.example.calymayor.domain.repository.CatalogueLocalRepository
import com.example.calymayor.domain.repository.CatalogueRepository
import com.example.calymayor.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        Constants.NAME_DATABASE
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()


    @Provides
    @Singleton
    fun provideCatalogueLocalRepository(db: AppDataBase): CatalogueLocalRepository {
        return CatalogueLocalRepositoryImpl(db.selectOptionsDao())
    }

    @Provides
    @Singleton
    fun provideCatalogueRepository(api: ApiService): CatalogueRepository {
        return CatalogueRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideSelectOptionsDao(db : AppDataBase) = db.selectOptionsDao()

}