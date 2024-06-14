package com.example.theguide.presentation.visitedlist

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.theguide.domain.model.PlaceModel
import com.example.theguide.domain.resource.Resource
import com.google.android.gms.tasks.Tasks
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
class VisitedListVM @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(VisitedListState())
    val state = _state.asStateFlow()

    private val visitedListCollection = Firebase.firestore.collection("users")

    fun onAction(action: VisitedListAction) {
        when (action) {
            is VisitedListAction.LoadVisitedList -> {
                getVisitedList(action.userId)
            }
        }
    }

    private fun getVisitedList(userId: String) {
        val document = visitedListCollection.document(userId).collection("visitedlist")

        CoroutineScope(Dispatchers.IO).launch {
            try {
                _state.update {
                    it.copy(isLoading = true)
                }
                val result = Tasks.await(document.get())
                if (result.isEmpty) {
                    _state.update {
                        it.copy(
                            visitedList = emptyList(),
                            isLoading = false
                        )
                    }
                } else {
                    val visitedList = result.toObjects(PlaceModel::class.java)
                        .sortedByDescending { it.userRating }
                    _state.update {
                        it.copy(
                            visitedList = visitedList,
                            isLoading = false
                        )
                    }
                }
            } catch (exception: Exception) {
                _state.update {
                    it.copy(isLoading = false)
                }
                Log.d("getVisitedList", "Error getting documents: ", exception)
            }
        }
    }
}