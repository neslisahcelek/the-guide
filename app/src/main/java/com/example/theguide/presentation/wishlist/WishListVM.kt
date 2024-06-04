package com.example.theguide.presentation.wishlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theguide.domain.model.PlaceModel
import com.example.theguide.domain.usecase.place.AddRatingUseCase
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListVM @Inject constructor(
    private val addRatingUseCase: AddRatingUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(WishListState())
    val state = _state.asStateFlow()

    private val wishListCollection = Firebase.firestore.collection("users")

    fun onAction(action: WishListAction) {
        when (action) {
            is WishListAction.LoadWishList -> {
                getWishList(action.userId)
            }

            is WishListAction.RemoveFromWishList -> removeFromWishList(action.userId, action.place)
            is WishListAction.RatePlace -> {
                addRating(action.userId, action.place.id, action.rating)
                addToVisitedList(action.userId, action.place, action.rating)
            }
        }
    }

    private fun getWishList(userId: String) {
        val document = wishListCollection.document(userId).collection("wishlist")

        _state.update {
            it.copy(wishList = emptyList()) //loading
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = Tasks.await(document.get())
                if (result.isEmpty) {
                    _state.update {
                        it.copy(wishList = emptyList())
                    }
                } else {
                    val wishList = result.toObjects(PlaceModel::class.java)
                    _state.update {
                        it.copy(wishList = wishList)
                    }
                }
            } catch (exception: Exception) {
                Log.d("getWishList", "Error getting documents: ", exception)
            }
        }
    }

    private fun addRating(userId: String, placeId: Int, rating: Double) {
        viewModelScope.launch {
            val result = addRatingUseCase.execute(
                userId = userId,
                placeId = placeId,
                rating = rating
            )
            Log.d("Wishlist", "addRating: ${result.data} ${result.message}")
        }
    }

    private fun addToVisitedList(userId: String?, place: PlaceModel, rating: Double) {
        if (userId == null) {
            return
        }
        val document =
            wishListCollection.document(userId).collection("visitedlist")
                .document(place.id.toString())
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Tasks.await(
                    document.set(
                        place.toMap(userRating = rating, isUserRated = true),
                        SetOptions.merge()
                    )
                )
            } catch (exception: Exception) {
                Log.d("addToWishList", "Error adding document: ", exception)
            }
        }
        removeFromWishList(userId, place)
    }

    private fun removeFromWishList(userId: String?, wish: PlaceModel) {
        if (userId == null) {
            Log.d("removeFromWishList error", "userId is null")
            return
        }
        val document =
            wishListCollection.document(userId).collection("wishlist").document(wish.id.toString())

        CoroutineScope(Dispatchers.IO).launch {
            try {
                Tasks.await(document.delete())
                Log.d("firebase", "wish deleted!")
            } catch (e: Exception) {
                Log.w("firebase error", "Error deleting document", e)
            }
        }

        _state.update {
            it.copy(
                wishList = it.wishList - wish
            )
        }
    }
}
