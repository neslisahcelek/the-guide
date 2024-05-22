package com.example.theguide.data.repository

import com.example.theguide.data.local.UserEntity
import com.example.theguide.data.local.UserDao
import javax.inject.Inject

class UserDaoImpl @Inject constructor(private val userDao: UserDao) : UserDao {
    override suspend fun upsertUser(user: UserEntity) = userDao.upsertUser(user)

    override suspend fun deleteUser(user: UserEntity) = userDao.deleteUser(user)

    override suspend fun deleteAllUsers() = userDao.deleteAllUsers()

    override suspend fun getUser(): UserEntity? = userDao.getUser()
}