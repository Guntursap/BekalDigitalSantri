package com.guntur.santripunya.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "wirid")
@Parcelize
data class Entity(
    @field:ColumnInfo(name = "wiridId")
    @field:PrimaryKey(autoGenerate = true)
    val wiridId: Int = 0,

    @field:ColumnInfo(name = "wiridIndo")
    val wiridIndo: String,

    @field:ColumnInfo(name = "wiridArab")
    val wiridArab: String,

    @field:ColumnInfo(name = "startCount")
    val startCount: Int,

    @field:ColumnInfo(name = "count")
    val count: Int

): Parcelable