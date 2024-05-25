package com.example.theguide.presentation.wishlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theguide.domain.usecase.wishlist.WishListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListVM @Inject constructor(
    private val wishListUseCases: WishListUseCases
) : ViewModel(){
    private val _state = MutableStateFlow(WishListState())
    val state = _state.asStateFlow()

    fun onAction(action: WishListAction) {
        when (action) {
            is WishListAction.LoadWishList -> {
                getWishList(action.userId)
            }
        }
    }

    private fun getWishList(userId: String) {
        viewModelScope.launch{
            val result = wishListUseCases.getWishListUseCase.execute(userId)
            if (result.data != null) {
                _state.update {
                    it.copy(
                        wishList = listOf()//result.data
                    )
                }
                Log.d("getWishList", "Success: ${result.data}")
            } else {
                Log.d("getWishList", "Error $result.message ?:")
            }
        }
    }
}
