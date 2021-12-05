package com.ejavinas.trackbyitunes.itunessearch.repository.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ejavinas.trackbyitunes.itunessearch.repository.local.room.daos.TrackDao
import com.ejavinas.trackbyitunes.itunessearch.repository.local.room.entities.TrackEntity

/**
 * Database for ITunes Search
 */
@Database(entities = [TrackEntity::class], version = 1, exportSchema = false)
abstract class ItunesSearchDatabase : RoomDatabase() {

    abstract fun trackDao(): TrackDao

    companion object {
        private const val DB_NAME = "itunes-search-db"

        // For Singleton instantiation
        @Volatile private var instance: ItunesSearchDatabase? = null

        fun getInstance(context: Context): ItunesSearchDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ItunesSearchDatabase {
            return Room.databaseBuilder(context, ItunesSearchDatabase::class.java, DB_NAME)
                .build()
        }
    }

}