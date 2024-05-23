package com.example.theguide.presentation.dashboard

import Recommendation

data class DashboardState(
    val places : List<Recommendation> = emptyList(),
    val isLoading : Boolean = false,
    val error : String? = null,
    val userId : String = "",
    val wishList : List<Int> = emptyList()
)