package com.project.pokemonspeare.controller

import com.project.pokemonspeare.model.TranslatedPokemon
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

interface PokemonShakespeareController {

    @GetMapping("/pokemon/{name}")
    fun getTranslatedPokemon(@PathVariable name: String) : ResponseEntity<TranslatedPokemon>

}