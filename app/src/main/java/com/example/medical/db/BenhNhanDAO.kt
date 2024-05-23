package com.example.medical.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.medical.entity.BenhNhan

@Dao
interface BenhNhanDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBenhNhan(benhNhan: BenhNhan)

    @Query("SELECT * FROM benhnhan WHERE userID = :userID")
    fun getBNbyUserId(userID : Int) : LiveData<List<BenhNhan>>




}