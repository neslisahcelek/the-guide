package com.example.theguide.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("DELETE FROM 'UserEntity'")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM UserEntity")
    fun getUser(): UserEntity?

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    fun isUserLoggedIn(id: String): Boolean
}