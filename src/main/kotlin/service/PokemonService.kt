package com.project.pokemonspeare.service

import com.project.pokemonspeare.model.Pokemon

interface PokemonService {

    fun retrievePokemon(name: String) : Pokemon?
}