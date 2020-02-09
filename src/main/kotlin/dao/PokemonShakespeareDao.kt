package com.project.pokemonspeare.dao

import com.project.pokemonspeare.model.ShakespeareDescription
import com.project.pokemonspeare.model.TranslatedPokemon
import java.time.Instant

interface PokemonShakespeareDao{

    fun savePokemonBackuped(name: String, shakespeareDescription: ShakespeareDescription, createdOn: Instant) : Boolean

    fun getPokemonBackuped(name: String) : TranslatedPokemon?

}