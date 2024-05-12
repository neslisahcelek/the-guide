package com.example.theguide.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.theguide.data.local.UserEntity

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity")
    fun getUser(): UserEntity

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    fun isUserLoggedIn(id: String): Boolean
}