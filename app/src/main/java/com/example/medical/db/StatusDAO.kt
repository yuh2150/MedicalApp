package com.example.medical.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.medical.entity.Status

@Dao
interface StatusDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStatus(status: Status)

    @Query("SELECT * FROM status ORDER BY id_status ASC")
    fun readAllData() : LiveData<List<Status>>

}