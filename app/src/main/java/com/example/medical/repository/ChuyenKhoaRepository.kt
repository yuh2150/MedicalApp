package com.example.medical.repository

import androidx.lifecycle.LiveData
import com.example.medical.db.ChuyenKhoaDAO
import com.example.medical.entity.BacSi
import com.example.medical.entity.ChuyenKhoa

class ChuyenKhoaRepository(private val chuyenkhoaDao : ChuyenKhoaDAO) {
    val readAllData: LiveData<List<ChuyenKhoa>> = chuyenkhoaDao.readAllData()

    suspend fun addChuyenkhoa(chuyenkhoa: ChuyenKhoa){
        chuyenkhoaDao.addChuyenkhoa(chuyenkhoa)
    }
    fun getBacSiByIdCk(id_ck : Int) : LiveData<List<BacSi>> {
        return chuyenkhoaDao.getBacSiByIdCk(id_ck)
    }
}