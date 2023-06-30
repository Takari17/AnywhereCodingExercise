package com.takari.anywherecodingexercise.data.characterResponse

import kotlinx.serialization.Serializable

@Serializable
data class RelatedTopic(
    val FirstURL: String,
    val Icon: Icon,
    val Result: String,
    val Text: String
)