package com.project.pokemonspeare.service

import com.project.pokemonspeare.model.TranslatedPokemon

interface PokemonShakespeareService{

    /**
     * This method return a shakespearian description of a pokemon.
     * @param name This is the name of pokemon to translate
     * @return TranslatedPokemon object. An object with name and description.
     */
    fun getTranslatedPokemon(name: String) : TranslatedPokemon
}