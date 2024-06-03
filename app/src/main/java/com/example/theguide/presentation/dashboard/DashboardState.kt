package com.example.theguide.presentation.dashboard

import com.example.theguide.domain.model.PlaceModel

data class DashboardState(
    val places: List<PlaceModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val userId: String = "",
)

data class CheckboxState(
    val text: String,
    val isChecked: Boolean = false
)