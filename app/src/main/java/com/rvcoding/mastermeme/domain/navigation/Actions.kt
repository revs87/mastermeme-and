package com.rvcoding.mastermeme.domain.navigation

sealed interface Actions {
    sealed interface YourMemes : Actions {
        data object OpenChoseTemplate : YourMemes
        data class OnMemeClick(val memeId: Int) : YourMemes
        data class OnMemeLongClick(val memeId: Int) : YourMemes
    }
}