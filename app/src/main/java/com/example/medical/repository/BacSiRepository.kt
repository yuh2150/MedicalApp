package com.example.medical.repository

import androidx.lifecycle.LiveData
import com.example.medical.db.BacSiDAO
import com.example.medical.entity.BacSi
import com.example.medical.entity.BenhNhan
import com.example.medical.entity.KeHoachWithStatus
import com.example.medical.entity.LichHen
import com.example.medical.entity.Status

class BacSiRepository(private val bacsiDao : BacSiDAO) {
    val readAllData: LiveData<List<BacSi>> = bacsiDao.readAllData()
    fun readLimit(number : Int): LiveData<List<BacSi>> {
        return bacsiDao.readLimit(number)
    }


    suspend fun addBacSi(bacSi: BacSi){
        bacsiDao.addBacSi(bacSi)
    }

    suspend fun addLichHen(lichHen: LichHen){
        bacsiDao.addLichHen(lichHen)
    }

    suspend fun addBenhNhan(benhNhan : BenhNhan){
        bacsiDao.addBenhNhan(benhNhan)
    }
    fun getBenhNhanById(userID : String): LiveData<List<BenhNhan>> {
        return bacsiDao.getBenhNhanById(userID)
    }

    fun getNgayByIdBs(id_bs : Int): LiveData<List<String>> {
         return bacsiDao.getNgayByIdBs(id_bs)
    }
    fun getGioByIdBsAndNgay(id_bs: Int, ngay: String): LiveData<List<KeHoachWithStatus>>{
        return bacsiDao.getGioByIdBsAndNgay(id_bs,ngay)
    }
    fun getStatusByIdKh(id_kh:  Int ): LiveData<Status>{
        return bacsiDao.getStatusByIdKh(id_kh)
    }
    fun getBacSiByIdKh(id_kh:  Int ): LiveData<BacSi>{
        return bacsiDao.getBacSiByIdKh(id_kh)
    }
//    val getNgayByIdBs : List<String> = bacsiDao.getNgayByIdBs()
}