package com.example.theguide.domain.usecase

import com.example.theguide.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntryUseCase(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(): Flow<Boolean> = localUserManager.readAppEntry()
}