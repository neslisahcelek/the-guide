package com.example.theguide.presentation.visitedlist

import com.example.theguide.domain.model.PlaceModel

data class VisitedListState(
    var visitedList: List<PlaceModel>? = null
)