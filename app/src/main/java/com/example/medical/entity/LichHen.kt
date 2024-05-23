package com.example.medical.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lichhen")
data class LichHen (
    @PrimaryKey(autoGenerate = true)
    val id_lh: Int,
    val id_bn:Int?= null,
    val id_bs : Int?= null ,
    val id_kh : Int?= null,
    val lydo : String?= null ,
    val trangthai :Int?= null
)