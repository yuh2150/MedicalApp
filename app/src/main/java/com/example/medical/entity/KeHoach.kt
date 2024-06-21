package com.example.medical.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "kehoach")
data class KeHoach (
    @PrimaryKey(autoGenerate = true)
    val id_kh : Int,
    var id_bs : Int?= null,
    var id_status :Int ?= null,
    val trangthai : Int ?= null
): Parcelable

data class KeHoachWithStatus(

    val gio :String,
    @Embedded
    val keHoach: KeHoach? = null

)