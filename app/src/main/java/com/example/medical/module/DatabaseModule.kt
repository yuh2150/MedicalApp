package com.example.medical.module

import com.example.medical.db.ChuyenKhoaDAO

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.medical.db.BacSiDAO
import com.example.medical.db.BenhNhanDAO
import com.example.medical.db.LichHenDAO
import com.example.medical.entity.BacSi
import com.example.medical.entity.BenhNhan
import com.example.medical.entity.ChuyenKhoa
import com.example.medical.entity.KeHoach
import com.example.medical.entity.LichHen
import com.example.medical.entity.Status

@Database(entities = [ChuyenKhoa::class, BacSi::class,KeHoach::class,Status::class,BenhNhan :: class , LichHen :: class], version = 5, exportSchema = false)
abstract class DatabaseModule : RoomDatabase() {
    abstract fun chuyenkhoaDAO(): ChuyenKhoaDAO
    abstract fun bacsiDAO(): BacSiDAO
    abstract fun benhnhaDAO() :BenhNhanDAO
    abstract fun lichhenDAO() : LichHenDAO



    companion object {
        @Volatile
        private var INSTANCE: DatabaseModule? = null
        fun getDatabase(context: Context): DatabaseModule {
            val tempinstance = INSTANCE
            if (tempinstance != null) {
                return tempinstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseModule::class.java,
                    "medicalDB"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }

        }
    }
}