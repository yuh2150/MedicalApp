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
    var userID :  String?= null,
    var name : String?= null,
    var sdt : String?= null,
    val gioitinh :Int?= null,
    var ngaysinh : String?= null,
    var tinh : String?= null,
    var huyen : String?= null,
    var xa : String?= null,
    var diachi : String?= null
): Parcelable