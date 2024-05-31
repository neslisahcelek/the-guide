package com.example.theguide.presentation.wishlist

import com.example.theguide.domain.model.PlaceModel

sealed class WishListAction {
    data class LoadWishList(val userId: String) : WishListAction()
    data class RemoveFromWishList(val userId: String?, val place: PlaceModel) : WishListAction()
    data class RatePlace(val userId: String, val placeId: Int, val rating: Double) : WishListAction()
}
