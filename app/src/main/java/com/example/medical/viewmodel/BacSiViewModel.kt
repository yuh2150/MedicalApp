package com.example.medical.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.medical.db.BacSiDAO
import com.example.medical.entity.BacSi
import com.example.medical.entity.BenhNhan
import com.example.medical.entity.KeHoachWithStatus
import com.example.medical.entity.LichHen
import com.example.medical.entity.Status
import com.example.medical.module.DatabaseModule
import com.example.medical.repository.BacSiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BacSiViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData : LiveData<List<BacSi>>
    private  val repository: BacSiRepository



    init {
        val bacsiDAO = DatabaseModule.getDatabase(application).bacsiDAO()
        repository = BacSiRepository(bacsiDAO)
        readAllData = repository.readAllData

    }

    fun addBacSi(bacSi: BacSi){
        viewModelScope.launch (Dispatchers.IO){
            repository.addBacSi(bacSi)
        }
    }
    fun addLichHen(lichHen: LichHen){
        viewModelScope.launch (Dispatchers.IO){
            repository.addLichHen(lichHen)
        }
    }

    fun addBenhNhan(benhNhan: BenhNhan){
        viewModelScope.launch (Dispatchers.IO){
            repository.addBenhNhan(benhNhan)
        }
    }

    fun getBenhNhanById(userID : String): LiveData<List<BenhNhan>> {
        return repository.getBenhNhanById(userID)
    }

    fun getNgayListByIdBs(id_bs: Int): LiveData<List<String>> {

        return repository.getNgayByIdBs(id_bs)
    }

    fun getGioListByIdBsAndNgay(id_bs: Int, ngay:String): LiveData<List<KeHoachWithStatus>> {

        return repository.getGioByIdBsAndNgay(id_bs,ngay)
    }
    fun getStatusByIdKh(id_kh:  Int ): LiveData<Status> {
        return repository.getStatusByIdKh(id_kh)
    }

    fun getBacSiByIdKh(id_kh:  Int ): LiveData<BacSi> {
        return repository.getBacSiByIdKh(id_kh)
    }
    fun readLimit(number : Int): LiveData<List<BacSi>> {
        return repository.readLimit(number)
    }
}