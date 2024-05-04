package com.example.theguide.domain.usecase.place

import com.example.theguide.data.remote.UserInfo
import com.example.theguide.domain.repository.PlaceRepository
import com.example.theguide.domain.resource.Resource
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {
    suspend fun execute(info: UserInfo): Resource<String> {
        return placeRepository.createUser(info)
    }
    //suspend operator fun invoke() = placeRepository.getUserId()
}