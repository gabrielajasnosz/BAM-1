package com.example.gj_bam_1.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDataDao {
    @Query("SELECT * FROM userData")
    fun getAll(): List<UserData>

    @Insert
    fun insertAll(vararg data: UserData)

    @Delete
    fun delete(userData: UserData)
}