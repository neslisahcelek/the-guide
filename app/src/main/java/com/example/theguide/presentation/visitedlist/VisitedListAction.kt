package com.example.theguide.presentation.visitedlist

sealed class VisitedListAction {
    data class LoadVisitedList(val userId: String) : VisitedListAction()
}