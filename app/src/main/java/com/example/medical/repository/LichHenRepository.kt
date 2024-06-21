package com.example.medical.repository

import androidx.lifecycle.LiveData
import com.example.medical.db.BacSiDAO
import com.example.medical.db.LichHenDAO
import com.example.medical.entity.BacSi
import com.example.medical.entity.BenhNhan
import com.example.medical.entity.LichHen
import com.example.medical.entity.Status


class LichHenRepository(private val lichhenDao : LichHenDAO) {

    val readAllData: LiveData<List<LichHen>> = lichhenDao.readAllData()


    fun getStatusByIdKh(id_kh:  Int ): LiveData<Status>{
        return lichhenDao.getStatusByIdKh(id_kh)
    }
    fun getBacSiByIdKh(id_kh:  Int ): LiveData<BacSi>{
        return lichhenDao.getBacSiByIdKh(id_kh)
    }
    fun getBenhNhanByIdBn(id_bn : Int) : LiveData<BenhNhan>{
        return lichhenDao.getBenhNhanByIdBn(id_bn)
    }
    fun confirmLich(id_lh:  Int ) {
        return lichhenDao.confirmLich(id_lh)
    }
    fun confirmKeHoach(id_kh:  Int ) {
        return lichhenDao.confirmKeHoach(id_kh)
    }
}