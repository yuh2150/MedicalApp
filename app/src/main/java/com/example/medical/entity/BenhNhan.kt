package com.example.medical.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "benhnhan")
data class BenhNhan (
    @PrimaryKey(autoGenerate = true)
    val id_bn: Int,
    val userID :  Int?= null,
    val name : String?= null,
    val sdt : String?= null,
    val gioitinh :Int?= null,
    val ngaysinh : String?= null,
    val tinh : String?= null,
    val huyen : String?= null,
    val xa : String?= null,
    val diachi : String?= null
): Parcelable