package com.example.medical.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.medical.entity.LichHen

@Dao
interface LichHenDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLichHen(lichHen: LichHen)


}