package com.example.gj_bam_1.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserData::class], version = 1)
abstract class UserDataDatabase : RoomDatabase() {
    abstract fun userDataDao(): UserDataDao
}