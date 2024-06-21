package com.example.medical.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "bacsi")
data class BacSi (
    @PrimaryKey(autoGenerate = true)
    val id_bs: Int,
    val userID: String?= null,
    val id_ck: Int?= null,
    val name: String?= null,
    val hinhanh: String?= null,
    val chucvu: String?= null,
    val lamviec: String?= null,
    val thanhvien: String?= null,
    val daotao: String?= null,
    val kinhnghiem: String?= null,
    val chuyenmon:String?= null,
    val mota: String?= null,
    val giakham: Int?= null
): Parcelable