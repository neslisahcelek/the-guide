package com.example.theguide.presentation.dashboard

import com.example.theguide.domain.model.PlaceModel
import com.example.theguide.domain.model.User

sealed class DashboardAction {
    data class LoadDashboard(var userId: String?) : DashboardAction()
    data class AddToWishList(val userId: String?, val place: PlaceModel) : DashboardAction()
    data class RemoveFromWishList(val userId: String?, val place: PlaceModel) : DashboardAction()
    data class FilterDistricts(val districts: List<CheckboxState>, val userId: String?) : DashboardAction()
}