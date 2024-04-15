package com.example.theguide.domain.usecase.appentry

import com.example.theguide.domain.manager.LocalUserManager

class SaveAppEntryUseCase(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() = localUserManager.saveAppEntry()
}