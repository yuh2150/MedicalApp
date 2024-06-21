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
import com.example.medical.repository.LichHenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LichHenViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData : LiveData<List<LichHen>>
    private  val repository: LichHenRepository



    init {
        val lichhenDAO = DatabaseModule.getDatabase(application).lichhenDAO()
        repository = LichHenRepository(lichhenDAO)
        readAllData = repository.readAllData

    }

    fun getStatusByIdKh(id_kh:  Int ): LiveData<Status> {
        return repository.getStatusByIdKh(id_kh)
    }

    fun getBacSiByIdKh(id_kh:  Int ): LiveData<BacSi> {
        return repository.getBacSiByIdKh(id_kh)
    }

    fun getBenhNhanByIdBn(id_bn:  Int ): LiveData<BenhNhan> {
        return repository.getBenhNhanByIdBn(id_bn)
    }

    fun confirmLich(id_lh:  Int ) {
        return repository.confirmLich(id_lh)
    }

    fun confirmKeHoach(id_kh:  Int ) {
        return repository.confirmKeHoach(id_kh)
    }

}