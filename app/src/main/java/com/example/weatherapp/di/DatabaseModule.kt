package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.source.local.LocalDB
import com.example.weatherapp.data.source.local.dao.LocationDAO
import com.example.weatherapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): LocalDB {
        return Room.databaseBuilder(
            applicationContext,
            LocalDB::class.java,
            Constants.Config.DATABASE
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideLocationDAO(localDB: LocalDB): LocationDAO {
        return localDB.locationDAO()
    }
}
