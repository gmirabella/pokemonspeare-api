package com.project.pokemonspeare.model

data class Pokemon(
        val id: Long,
        val name: String,
        val species: Species
)

data class Species(
        val name : String,
        val url: String
)
