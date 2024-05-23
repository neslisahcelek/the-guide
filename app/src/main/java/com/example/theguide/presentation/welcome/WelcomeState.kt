package com.example.theguide.presentation.welcome

import Recommendation
import com.example.theguide.R
import com.example.theguide.domain.model.Place

data class WelcomeState (
    var placeList: List<Recommendation> = listOf(),
    var currentPlace: Recommendation =  Recommendation(
        placeName = "Walkers",
        address = "Kültür",
        mapsUrl = "https://www.google.com/maps/search/?api=1&query=36.8465237%2C30.7597125&query_place_id=ChIJ91Ez--ibwxQRFdcL4FiFLNc",
        rating = 4.5,
        photos = listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQR_abBtnzBFl_-kLkB-fbC-nskMexTTiE7w9GroVJTGA&s"),
        openingHours = listOf("Monday: 9:00 AM – 5:00 PM", "Tuesday: 9:00 AM – 5:00 PM", "Wednesday: 9:00 AM – 5:00 PM", "Thursday: 9:00 AM – 5:00 PM", "Friday: 9:00 AM – 5:00 PM", "Saturday: 9:00 AM – 5:00 PM", "Sunday: 9:00 AM – 5:00 PM"),
        types = listOf("Cafe"),
        reviews = listOf()
    ),
    var currentPlaceIndex: Int = 0,
    var isListCompleted: Boolean = false,
    var userName: String = "",
    var userId: String = ""
)