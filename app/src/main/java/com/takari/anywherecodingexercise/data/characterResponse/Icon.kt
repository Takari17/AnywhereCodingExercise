package com.takari.anywherecodingexercise.data.characterResponse

import kotlinx.serialization.Serializable

@Serializable
data class Icon(
    val Height: String,
    val URL: String,
    val Width: String
)