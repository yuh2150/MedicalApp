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
import com.example.medical.repository.StatusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatusViewModel (application: Application) : AndroidViewModel(application) {
    val readAllData : LiveData<List<Status>>
//    val readAllDataBacSi: LiveData<List<BacSi>>
//    val readAllDataStatus: LiveData<List<Status>>
    private  val repository: StatusRepository
    init {
        val StatusDAO = DatabaseModule.getDatabase(application).statusDAO()
        repository = StatusRepository(StatusDAO)
        readAllData = repository.readAllData
//        readAllDataBacSi = repository.readAllDataBacSi
//        readAllDataStatus = repository.readAllDataStatus
    }

    fun addStatus(status: Status){
        viewModelScope.launch (Dispatchers.IO){
            repository.addStatus(status)
        }
    }
//    fun getStatusByIdKh(id_kh:  Int ): LiveData<Status> {
//        return repository.getStatusByIdKh(id_kh)
//    }
//
//    fun getBacSiByIdKh(id_kh:  Int ): LiveData<BacSi> {
//        return repository.getBacSiByIdKh(id_kh)
//    }
}