package com.example.infinitescroll.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.infinitescroll.data.model.Apod

@Dao
interface ApodDao {

    @Query("SELECT * FROM apods ORDER BY date LIMIT :pageSize OFFSET :pageIndex")
    suspend fun getApods(pageSize : Int = 30, pageIndex : Int = 0): List<Apod>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApod(apod: Apod): Long

}