package com.example.infinitescroll.data.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.infinitescroll.data.model.Apod

@Dao
interface ApodDao {

    @Query("SELECT * FROM apods ORDER BY date DESC")
    fun getApods(): DataSource.Factory<Int, Apod>

    @Query("SELECT * FROM apods ORDER BY date LIMIT 1")
    suspend fun getLastApod(): Apod?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApod(apod: Apod): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApodList(objects: List<Apod>)

}