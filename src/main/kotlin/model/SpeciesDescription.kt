package com.project.pokemonspeare.model

data class SpeciesDescription(val flavor_text_entries: List<Description>)

data class Description(
        val flavor_text: String,
        val language: Language,
        val version: Version
)

data class Language(val name: String)

data class Version( val name: String)