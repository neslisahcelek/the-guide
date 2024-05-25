package com.example.theguide.presentation.wishlist

sealed class WishListAction {
    data class LoadWishList(val userId: String) : WishListAction()
}
