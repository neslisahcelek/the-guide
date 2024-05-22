package com.example.theguide.presentation.dashboard

import Recommendation
import com.example.theguide.domain.model.Place

data class DashboardState(
    val places : List<Recommendation> = emptyList(),
    val isLoading : Boolean = false,
    val error : String? = null,
    val userId : String = ""
)