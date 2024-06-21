package com.example.medical.repository

import androidx.lifecycle.LiveData
import com.example.medical.db.KeHoachDAO
import com.example.medical.entity.BacSi
import com.example.medical.entity.KeHoach
import com.example.medical.entity.Status
import com.example.medical.module.DatabaseModule

class KeHoachRepository(private val KeHoachDao: KeHoachDAO) {
    val readAllData: LiveData<List<KeHoach>> = KeHoachDao.readAllData()

    val readAllDataBacSi: LiveData<List<BacSi>> = KeHoachDao.readAllDataBacSi()

    val readAllDataStatus: LiveData<List<Status>> = KeHoachDao.readAllDataStatus()
    suspend fun addKeHoach(KeHoach: KeHoach){
        KeHoachDao.addKehoach(KeHoach)
    }

    fun getStatusByIdKh(id_kh:  Int ): LiveData<Status>{
        return KeHoachDao.getStatusByIdKh(id_kh)
    }
    fun getBacSiByIdKh(id_kh:  Int ): LiveData<BacSi>{
        return KeHoachDao.getBacSiByIdKh(id_kh)
    }
}