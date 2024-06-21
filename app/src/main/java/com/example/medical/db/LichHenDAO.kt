package com.example.medical.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.medical.entity.BacSi
import com.example.medical.entity.BenhNhan
import com.example.medical.entity.LichHen
import com.example.medical.entity.Status

@Dao
interface LichHenDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLichHen(lichHen: LichHen)
    @Query("SELECT * FROM lichhen ORDER BY id_lh ASC")
    fun readAllData() : LiveData<List<LichHen>>
    @Query("SELECT s.*  FROM kehoach as k,status as s WHERE k.id_kh = :id_kh and k.id_status = s.id_status; ")
    fun getStatusByIdKh(id_kh:  Int ): LiveData<Status>

    @Query("SELECT b.*  FROM kehoach as k,bacsi as b WHERE k.id_kh = :id_kh and k.id_bs = b.id_bs LIMIT 1; ")
    fun getBacSiByIdKh(id_kh:  Int ): LiveData<BacSi>

    @Query("SELECT bn.*  FROM lichhen as lh,benhnhan as bn WHERE lh.id_bn = :id_bn and lh.id_bn = bn.id_bn LIMIT 1; ")
    fun getBenhNhanByIdBn(id_bn:  Int ): LiveData<BenhNhan>

    @Query("UPDATE lichhen SET trangthai = 1 WHERE id_lh = :id_lh ; ")
    fun confirmLich(id_lh:  Int )

    @Query("UPDATE kehoach SET trangthai = 1 WHERE id_kh = :id_kh ; ")
    fun confirmKeHoach(id_kh:  Int )
}