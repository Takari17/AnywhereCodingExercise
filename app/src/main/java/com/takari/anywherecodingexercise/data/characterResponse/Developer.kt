package com.takari.anywherecodingexercise.data.characterResponse

import kotlinx.serialization.Serializable

@Serializable
data class Developer(
    val name: String,
    val type: String,
    val url: String
)