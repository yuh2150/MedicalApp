package com.example.medical.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "chuyenkhoa")
data class ChuyenKhoa (
    @PrimaryKey(autoGenerate = true)
    val id_ck: Int,
    val name: String?= null,
    val hinhanh: String?= null,
    val mota: String?= null
): Parcelable