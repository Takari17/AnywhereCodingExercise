package com.takari.anywherecodingexercise.data

import anywherecodingexercise.DuckDuckGoService
import com.takari.anywherecodingexercise.data.characterResponse.CharacterResponse
import javax.inject.Inject


class CharacterRepository @Inject constructor(private val duckDuckGoService: DuckDuckGoService) {

    suspend fun getCharacters(): CharacterResponse {
        return duckDuckGoService.getCharacters()
    }
}