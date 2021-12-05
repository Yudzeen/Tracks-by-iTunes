package com.ejavinas.trackbyitunes.itunessearch.repository.local.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ejavinas.trackbyitunes.itunessearch.repository.local.room.entities.TrackEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Data access object for [TrackEntity]
 */
@Dao
interface TrackDao {

    @Query("SELECT * FROM track")
    fun getTracks(): Flowable<List<TrackEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTracks(tracks: List<TrackEntity>): Completable

    @Query("DELETE FROM track")
    fun clearTracks(): Completable

}