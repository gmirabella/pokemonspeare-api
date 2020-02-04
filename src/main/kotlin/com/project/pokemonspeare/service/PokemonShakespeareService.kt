package com.project.pokemonspeare.service

import com.project.pokemonspeare.model.TranslatedPokemon

interface PokemonShakespeareService{

    fun getTranslatedPokemon(name: String) : TranslatedPokemon
}