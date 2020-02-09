package com.project.pokemonspeare.dao

import com.project.pokemonspeare.model.DBPokemon
import java.time.Instant

interface PokemonShakespeareDao{

    fun savePokemonBackuped(name: String, description: String, translatedDescription: String, createdOn: Instant) : Boolean

    fun getPokemonBackuped(name: String) : DBPokemon?
}