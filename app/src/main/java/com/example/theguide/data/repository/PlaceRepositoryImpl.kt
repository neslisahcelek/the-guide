package com.example.theguide.data.repository

import com.example.theguide.data.mapper.toPlaceModel
import com.example.theguide.data.remote.PlacesAPI
import com.example.theguide.data.remote.UserInfo
import com.example.theguide.domain.model.Place
import com.example.theguide.domain.repository.PlaceRepository
import com.example.theguide.domain.resource.Resource

class PlaceRepositoryImpl constructor(
    private val placesAPI: PlacesAPI
) : PlaceRepository {
      override suspend fun getUserId(): Resource<String> {
        return try {
            val response = placesAPI.getUserId()
            Resource.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An error occurred (getUserId)")
        }
    }

    override suspend fun createUser(info: UserInfo): Resource<String> {
        return try {
            val userId = placesAPI.createUser(info)
            Resource.Success(userId.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An error occurred (addUser)")
        }
    }

    override suspend fun getPlaceName(): Resource<String> {
        return try {
            val response = placesAPI.getPlaceName()
            Resource.Success(response.placeName)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An error occurred (getPlaceName)")
        }
    }

    override suspend fun getRecommendation(userId: Int, recommendationLimit: Int): Resource<List<Place>> {
        return try {
            Resource.Success(
                placesAPI.getRecommendation(
                    userId = userId,
                    recommendationLimit = recommendationLimit
                ).toPlaceModel()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error("An error occurred (getPlaces)")
        }
    }

    override suspend fun addRating(userId: Int, placeId: Int, rating: Double): Resource<String> {
        return try {
            val response = placesAPI.addRating(userId, placeId, rating)
            Resource.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An error occurred (addRating)")
        }
    }
}