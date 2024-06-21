package com.example.medical.repository
import androidx.lifecycle.LiveData
import com.example.medical.db.StatusDAO
import com.example.medical.entity.Status

class StatusRepository(private val StatusDao : StatusDAO) {
    val readAllData: LiveData<List<Status>> = StatusDao.readAllData()

    suspend fun addStatus(Status: Status){
        StatusDao.addStatus(Status)
    }
}