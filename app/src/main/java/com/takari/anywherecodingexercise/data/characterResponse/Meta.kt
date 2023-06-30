package com.takari.anywherecodingexercise.data.characterResponse

import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val attribution: String?,
    val blockgroup: String?,
    val created_date: String?,
    val description: String,
    val designer: String?,
    val dev_date: String?,
    val dev_milestone: String,
    val developer: List<Developer>,
    val example_query: String,
    val id: String,
    val is_stackexchange: String?,
    val js_callback_name: String,
    val live_date: String?,
    val maintainer: Maintainer,
    val name: String,
    val perl_module: String,
    val producer: String?,
    val production_state: String,
    val repo: String,
    val signal_from: String,
    val src_domain: String,
    val src_id: Int,
    val src_name: String,
    val src_options: SrcOptions,
    val src_url: String?,
    val status: String,
    val tab: String,
    val topic: List<String>,
    val unsafe: Int
)