package com.example.theguide.presentation.dashboard

import com.example.theguide.domain.model.Place

data class DashboardState(
    val places : List<Place> = emptyList(),
    val isLoading : Boolean = false,
    val error : String? = null,
    val userId : String = ""
)