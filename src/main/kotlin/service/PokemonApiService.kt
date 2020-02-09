package com.project.pokemonspeare.service

import com.project.pokemonspeare.model.Pokemon
import com.project.pokemonspeare.model.SpeciesDescription

interface PokemonApiService {

    /**
     * This method is used to retrieve pokemon from PokeApi.
     * @param name This is the name of pokemon to retrieve
     * @return Pokemon This returns Pokemon Object.
     */
    fun retrievePokemon(name: String) : Pokemon

    /**
     * This method is used to retrieve description species from PokeApi.
     * @param name This is the url for retrieve species
     * @return SpeciesDescription This returns SpeciesDescription Object.
     */
    fun retrieveSpeciesDescription(url: String) : SpeciesDescription
}