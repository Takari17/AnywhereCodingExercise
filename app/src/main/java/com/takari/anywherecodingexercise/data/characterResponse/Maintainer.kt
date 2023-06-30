package com.takari.anywherecodingexercise.data.characterResponse

import kotlinx.serialization.Serializable

@Serializable
data class Maintainer(
    val github: String
)