package com.example.medical.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.medical.entity.BacSi
import com.example.medical.repository.KeHoachRepository
import com.example.medical.entity.KeHoach
import com.example.medical.entity.Status
import com.example.medical.module.DatabaseModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KeHoachViewModel (application: Application) : AndroidViewModel(application) {
    val readAllData : LiveData<List<KeHoach>>
    val readAllDataBacSi: LiveData<List<BacSi>>
    val readAllDataStatus: LiveData<List<Status>>
    private  val repository: KeHoachRepository
    init {
        val KeHoachDAO = DatabaseModule.getDatabase(application).kehoachDAO()
        repository = KeHoachRepository(KeHoachDAO)
        readAllData = repository.readAllData
        readAllDataBacSi = repository.readAllDataBacSi
        readAllDataStatus = repository.readAllDataStatus
    }

    fun addKeHoach(KeHoach : KeHoach){
        viewModelScope.launch (Dispatchers.IO){
            repository.addKeHoach(KeHoach)
        }
    }
    fun getStatusByIdKh(id_kh:  Int ): LiveData<Status> {
        return repository.getStatusByIdKh(id_kh)
    }

    fun getBacSiByIdKh(id_kh:  Int ): LiveData<BacSi> {
        return repository.getBacSiByIdKh(id_kh)
    }
}