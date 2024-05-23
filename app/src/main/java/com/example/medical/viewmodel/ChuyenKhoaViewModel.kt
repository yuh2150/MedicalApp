package com.example.medical.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.medical.entity.BacSi
//import com.example.medical.db.ChuyenKhoaDB
import com.example.medical.repository.ChuyenKhoaRepository
import com.example.medical.entity.ChuyenKhoa
import com.example.medical.module.DatabaseModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChuyenKhoaViewModel (application: Application) : AndroidViewModel(application) {
    val readAllData : LiveData<List<ChuyenKhoa>>
    private  val repository: ChuyenKhoaRepository
    init {
        val chuyenkhoaDAO = DatabaseModule.getDatabase(application).chuyenkhoaDAO()
        repository = ChuyenKhoaRepository(chuyenkhoaDAO)
        readAllData = repository.readAllData
    }

    fun addChuyenKhoa(chuyenkhoa : ChuyenKhoa){
        viewModelScope.launch (Dispatchers.IO){
            repository.addChuyenkhoa(chuyenkhoa)
        }
    }
    fun getBacSiByIdCk(id_ck : Int) : LiveData<List<BacSi>> {
        return repository.getBacSiByIdCk(id_ck)
    }
}