package com.project.pokemonspeare.service

import com.project.pokemonspeare.model.ShakespeareDescription

interface ShakespeareService{

    /**
     * This method is used to translate pokemon descroption.
     * @param description This is the a description of pokemon to translate
     * @return ShakespeareDescription This returns ShakespeareDescription Object that contains:.
     *      - translated (description translated in shakespeare style
     *      - text (original description)
     */
    fun translatePokemonDescription(description: String) : ShakespeareDescription
}