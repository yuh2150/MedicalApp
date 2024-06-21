package com.example.medical.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.medical.entity.BacSi
import com.example.medical.entity.KeHoach
import com.example.medical.entity.Status


@Dao
interface KeHoachDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addKehoach(kehoach: KeHoach)

    @Query("SELECT * FROM bacsi ORDER BY id_bs ASC")
    fun readAllDataBacSi() : LiveData<List<BacSi>>

    @Query("SELECT * FROM status ORDER BY id_status ASC")
    fun readAllDataStatus() : LiveData<List<Status>>

    @Query("SELECT * FROM kehoach ORDER BY id_kh ASC")
    fun readAllData() : LiveData<List<KeHoach>>

    @Query("SELECT s.*  FROM kehoach as k,status as s WHERE k.id_kh = :id_kh and k.id_status = s.id_status; ")
    fun getStatusByIdKh(id_kh:  Int ): LiveData<Status>

    @Query("SELECT b.*  FROM kehoach as k,bacsi as b WHERE k.id_kh = :id_kh and k.id_bs = b.id_bs LIMIT 1; ")
    fun getBacSiByIdKh(id_kh:  Int ): LiveData<BacSi>

}