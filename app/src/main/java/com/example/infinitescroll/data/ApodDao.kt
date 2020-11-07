package com.example.infinitescroll.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.infinitescroll.model.Apod

@Dao
interface ApodDao {

    @Query("SELECT * FROM apods ORDER BY date LIMIT :pageSize OFFSET :pageIndex")
    fun getApods(pageSize : Int = 30, pageIndex : Int = 1): LiveData<List<Apod>>

    @Insert
    suspend fun insertApod(apod: Apod): Long

}