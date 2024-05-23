package com.example.theguide.presentation.dashboard

import com.example.theguide.domain.model.User

sealed class DashboardAction {
    data class LoadDashboard(var user: User?) : DashboardAction()
    data class AddToWishList(val userId: String?, val placeId: Int) : DashboardAction()
    data class RemoveFromWishList(val userId: String?, val placeId: Int) : DashboardAction()
}