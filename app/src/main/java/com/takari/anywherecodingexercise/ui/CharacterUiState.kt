package com.takari.anywherecodingexercise.ui

import com.takari.anywherecodingexercise.data.characterResponse.RelatedTopic

data class CharacterUiState(
    val characters: List<RelatedTopic>,
    val clickedCharacter: RelatedTopic?,

    // If this UI state was more complex I would encapsulate it in a sealed class representing each state.
    val apiCallFailed: Boolean
)