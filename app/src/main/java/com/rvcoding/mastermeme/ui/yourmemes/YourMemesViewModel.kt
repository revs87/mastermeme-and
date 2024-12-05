package com.rvcoding.mastermeme.ui.yourmemes

import androidx.lifecycle.ViewModel
import com.rvcoding.mastermeme.domain.Meme
import com.rvcoding.mastermeme.domain.navigation.Actions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.rvcoding.mastermeme.R
import com.rvcoding.mastermeme.ui.theme.Drawables


class YourMemesViewModel : ViewModel() {
    private val _memes = MutableStateFlow<List<Meme>>(Drawables.memeMap.entries.map { Meme(it.key, it.value) })
    val memes: StateFlow<List<Meme>> = _memes.asStateFlow()

    fun onAction(action: Actions.YourMemes) {
        when (action) {
            is Actions.YourMemes.OnMemeClick -> {}
            is Actions.YourMemes.OnMemeLongClick -> {}
            Actions.YourMemes.OpenChoseTemplate -> {}
        }
    }
}