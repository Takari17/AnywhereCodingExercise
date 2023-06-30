package com.takari.anywherecodingexercise.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takari.anywherecodingexercise.data.CharacterRepository
import com.takari.anywherecodingexercise.data.characterResponse.RelatedTopic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        CharacterUiState(
            characters = emptyList(),
            clickedCharacter = null,
            apiCallFailed = false
        )
    )

    val uiState: StateFlow<CharacterUiState> = _uiState

    init {
        viewModelScope.launch {
            try {
                val characters = repository.getCharacters()

                _uiState.value = _uiState.value.copy(
                    characters = characters.RelatedTopics,
                    clickedCharacter = characters.RelatedTopics.first(),
                    apiCallFailed = false
                )

            } catch (e: Exception) {
                Log.d("takari", "Duckduckgo Api call failed: $e")

                _uiState.value = _uiState.value.copy(
                    apiCallFailed = true
                )
            }
        }
    }

    fun updateClickedCharacter(character: RelatedTopic) {
        _uiState.value = _uiState.value.copy(
            clickedCharacter = character
        )
    }

    /**
     * Returns the character name from the RelatedTopic.kt text field since the duckduckgo API
     * returns the character name and description in one text field in the
     * following format: "Homer Simpson - this character was created in...".
     *
     * @param nameAndDescription The text field returned by duckduckgo in RelatedTopic.kt
     *
     * @return the extracted name. If a name isn't able to be extracted nameAndDescription
     *  will be returned.
     */
    fun getCharacterName(nameAndDescription: String?): String? {
        return try {
            val endOfNameIndex = nameAndDescription!!.indexOf("-")

            nameAndDescription.slice(IntRange(0, endOfNameIndex - 1))
        } catch (e: Exception) {
            Log.d(
                "takari",
                "Unable to extract name from the following text: $nameAndDescription"
            )

            nameAndDescription
        }
    }

    /**
     * Returns the character description from the RelatedTopic.kt text field since the duckduckgo API
     * returns the character name and description in one text field in the
     * following format: "Homer Simpson - this character was created in...".
     *
     * @param nameAndDescription The text field returned by duckduckgo in RelatedTopic.kt
     *
     * @return the extracted description. If a description isn't able to be extracted
     *  nameAndDescription will be returned.
     */
    fun getCharacterDescription(nameAndDescription: String?): String? {
        return try {
            val endOfNameIndex = nameAndDescription!!.indexOf("-")

            nameAndDescription.slice(
                IntRange(
                    endOfNameIndex,
                    nameAndDescription.length - endOfNameIndex
                )
            )
        } catch (e: Exception) {
            Log.d(
                "takari",
                "Unable to extract description from the following text: $nameAndDescription"
            )

            nameAndDescription
        }
    }

    /**
     * Since the text field in RelatedTopic.kt contains both the name and description, this function
     * will check if the query substring is present in BOTH the name and description.
     *
     * I'm using linear search here since the dataset is small and static. Beam search would be
     * more appropriate if the dataset was large or needed to scale.
     *
     * @param query filter condition.
     * @return a filtered list only containing elements where the query substring is present
     *  in the name or description.
     */
    fun filterCharacters(query: String?): List<RelatedTopic> {
        if (query == null) {
            return uiState.value.characters
        }

        val filteredCharacters = uiState.value.characters.filter {
            it.Text.contains(query, ignoreCase = true)
        }

        return filteredCharacters
    }
}