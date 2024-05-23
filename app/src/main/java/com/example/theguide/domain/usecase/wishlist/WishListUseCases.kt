package com.example.theguide.domain.usecase.wishlist

data class WishListUseCases (
    val addToWishListUseCase: AddToWishListUseCase,
    val removeFromWishListUseCase: RemoveFromWishListUseCase,
    val getWishListUseCase: GetWishListUseCase,
)