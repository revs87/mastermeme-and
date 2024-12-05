package com.rvcoding.mastermeme.domain.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object YourMemes: Destination

    @Serializable
    data object YourMemeTemplates: Destination

    @Serializable
    data class NewMeme(val templateId: Int): Destination
}