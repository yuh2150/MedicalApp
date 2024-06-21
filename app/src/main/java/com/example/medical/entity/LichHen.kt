package com.example.medical.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "lichhen")
data class LichHen (
    @PrimaryKey(autoGenerate = true)
    val id_lh: Int,
    var id_bn:Int?= null,
    var id_bs : Int?= null,
    var id_kh : Int?= null,
    var lydo : String?= null,
    var trangthai :Int?= null
): Parcelable