package com.example.medical.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.medical.entity.BacSi
import com.example.medical.entity.KeHoachWithStatus
import com.example.medical.entity.Status
import java.util.ArrayList


@Dao
interface BacSiDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBacSi(bacSi: BacSi)

    @Query("SELECT * FROM bacsi ORDER BY id_bs ASC")
    fun readAllData() : LiveData<List<BacSi>>

    @Query("SELECT * FROM bacsi ORDER BY id_bs ASC LIMIT :number")
    fun readLimit(number : Int) : LiveData<List<BacSi>>

    @Query("SELECT DISTINCT s.ngay FROM kehoach as k, status as s WHERE k.id_bs = :id_bs and k.id_status = s.id_status")
    fun getNgayByIdBs(id_bs: Int): LiveData<List<String>>

    @Query("SELECT DISTINCT s.gio ,k.*  FROM kehoach as k,status as s WHERE k.id_bs = :id_bs and k.id_status = s.id_status and s.ngay = :ngay and k.trangthai=0 ;")
    fun getGioByIdBsAndNgay(id_bs: Int, ngay: String): LiveData<List<KeHoachWithStatus>>

    @Query("SELECT s.*  FROM kehoach as k,status as s WHERE k.id_kh = :id_kh and k.id_status = s.id_status; ")
    fun getStatusByIdKh(id_kh:  Int ): LiveData<Status>

    @Query("SELECT b.*  FROM kehoach as k,bacsi as b WHERE k.id_kh = :id_kh and k.id_bs = b.id_bs LIMIT 1; ")
    fun getBacSiByIdKh(id_kh:  Int ): LiveData<BacSi>
}