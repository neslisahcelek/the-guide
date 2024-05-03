package com.example.theguide.data.repository

import android.util.Log
import com.example.theguide.data.mapper.toPlaceModel
import com.example.theguide.data.remote.PlacesAPI
import com.example.theguide.data.remote.UserInfo
import com.example.theguide.domain.model.Place
import com.example.theguide.domain.repository.PlaceRepository
import com.example.theguide.domain.resource.Resource
import org.json.JSONArray
import org.json.JSONObject

class PlaceRepositoryImpl constructor(
    private val placesAPI: PlacesAPI
) : PlaceRepository {
    override suspend fun getPlaces(): Resource<List<Place>> {
        return try {
            Resource.Success(
                placesAPI.getPlaces("id").toPlaceModel()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error("An error occurred")
        }
    }

    override suspend fun getUserId(): Resource<String> {
        TODO("Not yet implemented")
    }

    /*
    override suspend fun getUserId(): Resource<String> {
        return try {
            val response = placesAPI.fetchData()
            val id = response.place_name
            Resource.Success(id.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An error occurred")
        }
    }

     */
    override suspend fun getUserId(info: UserInfo): Resource<String> {
        return try {
            val response = placesAPI.fetchData(info)
            val id = response.toString()
            Resource.Success(id.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}