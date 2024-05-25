package com.example.theguide.presentation.wishlist

import com.example.theguide.domain.model.PlaceModel

data class WishListState (
    var wishList: List<PlaceModel> = emptyList(),
)
