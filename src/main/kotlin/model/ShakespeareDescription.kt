package com.project.pokemonspeare.model

data class ShakespeareDescription(val contents: Content)

data class Content(
        val translated: String,
        val text: String
)