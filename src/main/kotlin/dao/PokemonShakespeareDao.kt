package com.project.pokemonspeare.dao

import com.project.pokemonspeare.model.ShakespeareDescription
import com.project.pokemonspeare.model.TranslatedPokemon
import java.time.Instant

interface PokemonShakespeareDao{
    /**
     * This method save all new translated pokemon.
     * @param name This is the name of pokemon to save
     * @param shakespeareDescription This an object with description and translatedDescription
     * @param createdOn This is created time
     * @return Boolean.
     */
    fun savePokemonBackuped(name: String, shakespeareDescription: ShakespeareDescription, createdOn: Instant) : Boolean

    /**
     * This method is used to retrieve saved pokemon into database.
     * @param name This is the name of pokemon to retrieve
     * @return TranslatedPokemon This returns TranslatedPokemon.
     */
    fun getPokemonBackuped(name: String) : TranslatedPokemon?

}