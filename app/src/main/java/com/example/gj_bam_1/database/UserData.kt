package com.example.gj_bam_1.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserData(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "number") val number: Int?
)