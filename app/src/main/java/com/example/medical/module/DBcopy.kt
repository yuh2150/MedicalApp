package com.example.medical.module

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.io.File
import java.io.FileOutputStream

class DBcopy(private val  context :  Context) {
    companion object {
        private val DB_NAME = "medicalDB"
    }
    fun openDatabase(): SQLiteDatabase{
        val dbFile = context.getDatabasePath(DB_NAME)
        val file = File(dbFile.toString())
        if (file.exists()){

        }else{
            copyDatabase(dbFile)
        }
        return SQLiteDatabase.openDatabase(dbFile.path,null,SQLiteDatabase.OPEN_READWRITE)
    }

    private fun copyDatabase(dbFile: File?) {
        val openDB = context.assets.open(DB_NAME)
        val outputStream = FileOutputStream(dbFile)
        val buffer = ByteArray(1024)
        while (openDB.read(buffer)>0){
            outputStream.write(buffer)
        }
        outputStream.flush()
        outputStream.close()
        openDB.close()
    }
}