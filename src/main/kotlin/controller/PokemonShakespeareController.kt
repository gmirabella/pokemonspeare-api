package com.project.pokemonspeare.controller

import com.project.pokemonspeare.model.TranslatedPokemon
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

interface PokemonShakespeareController {

    /**
     * This endpoint return a shakespearian description of a pokemon.
     * @param name This is the name of pokemon to translate
     * @return TranslatedPokemon object. An object with name and description.
     */
    @GetMapping("/pokemon/{name}")
    fun getTranslatedPokemon(@PathVariable name: String) : ResponseEntity<TranslatedPokemon>

}