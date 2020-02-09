package com.project.pokemonspeare.controller

import com.project.pokemonspeare.model.TranslatedPokemon
import com.project.pokemonspeare.service.PokemonShakespeareService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import javax.inject.Inject

@Controller
class PokemonShakespeareControllerImpl : PokemonShakespeareController {

    @Inject private lateinit var pokemonShakespeareService : PokemonShakespeareService

    override fun getTranslatedPokemon(name: String): ResponseEntity<TranslatedPokemon> {
        return ResponseEntity(pokemonShakespeareService.getTranslatedPokemon(name), HttpStatus.OK)
    }
}