package com.example.medical.entity

import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "status")
data class Status(
    @PrimaryKey(autoGenerate = true)
    val id_status: Int,
    val ngay: String  ?= null,
    val gio: String ?= null,

    )