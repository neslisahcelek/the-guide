package com.example.theguide.data.repository

import Recommendation
import com.example.theguide.data.remote.PlacesAPI
import com.example.theguide.data.remote.dto.RatingInfo
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

    override suspend fun createUser(userId: String): Resource<String> {
        return try {
            val result = placesAPI.createUser(userId)
            Resource.Success(result.message)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An error occurred (addUser)")
        }
    }

    override suspend fun addRating(userId: String, placeId: Int, rating: Double): Resource<String> {
        val ratingInfo = RatingInfo(userId, placeId, rating)
        return try {
            val response = placesAPI.addRating(ratingInfo)
            Resource.Success(response.message)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An error occurred (addRating)")
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

    override suspend fun getRecommendation(userId: String, recommendationLimit: Int): Resource<List<Recommendation>> {
        return try {
            Resource.Success(
                placesAPI.getRecommendation(
                    userId = userId,
                    recommendationLimit = recommendationLimit
                ).recommendations
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error("An error occurred (getPlaces)")
        }
    }


}