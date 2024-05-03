package com.example.theguide.domain.repository

import com.example.theguide.data.remote.UserInfo
import com.example.theguide.domain.model.Place
import com.example.theguide.domain.resource.Resource

interface PlaceRepository {
    suspend fun getPlaces(): Resource<List<Place>>

    suspend fun getUserId(): Resource<String>
    suspend fun getUserId(info: UserInfo): Resource<String>
}