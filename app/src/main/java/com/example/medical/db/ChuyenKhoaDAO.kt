package com.example.medical.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.medical.entity.BacSi
import com.example.medical.entity.ChuyenKhoa

@Dao
interface ChuyenKhoaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addChuyenkhoa(chuyenkhoa: ChuyenKhoa)

    @Query("SELECT * FROM chuyenkhoa ORDER BY id_ck ASC")
    fun readAllData() : LiveData<List<ChuyenKhoa>>

    @Query("SELECT b.* FROM chuyenkhoa as ck, bacsi as b WHERE b.id_ck = ck.id_ck AND ck.id_ck = :id_ck")
    fun getBacSiByIdCk(id_ck : Int) : LiveData<List<BacSi>>
}