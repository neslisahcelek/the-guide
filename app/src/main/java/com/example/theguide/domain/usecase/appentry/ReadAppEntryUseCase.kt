package com.example.theguide.domain.usecase.appentry

import com.example.theguide.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntryUseCase(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke(): Flow<Boolean> = localUserManager.readAppEntry()
}