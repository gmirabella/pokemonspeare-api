package com.project.pokemonspeare.service

import com.project.pokemonspeare.controller.PokemonShakespeareControllerImpl
import com.project.pokemonspeare.dao.PokemonShakespeareDao
import com.project.pokemonspeare.model.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.webjars.NotFoundException
import java.time.Instant
import javax.inject.Inject

@Service
class PokemonShakespeareServiceImpl : PokemonShakespeareService {

    @Inject private lateinit var pokemonApiService: PokemonApiService
    @Inject private lateinit var shakespeareService: ShakespeareService
    @Inject private lateinit var pokemonShakespeareDao: PokemonShakespeareDao

    @Value("\${pokemon.description.language}") private lateinit var language: String
    @Value("\${pokemon.description.version}") private lateinit var version: String


    private val log : Logger = LoggerFactory.getLogger(PokemonShakespeareControllerImpl::class.java)

    override fun getTranslatedPokemon(name: String): TranslatedPokemon {

        return pokemonShakespeareDao.getPokemonBackuped(name) ?: getNewPokemon(name)
        
    }

    private fun getNewPokemon(name: String) : TranslatedPokemon {
        val pokemon = pokemonApiService.retrievePokemon(name)
        val description = filterDescription(pokemonApiService.retrieveSpeciesDescription(pokemon.species.url))
        val shakespeareDescription = shakespeareService.translatePokemonDescription(description)

        pokemonShakespeareDao.savePokemonBackuped(pokemon.name, shakespeareDescription, Instant.now())

        return TranslatedPokemon(
                name = pokemon.name,
                description = shakespeareDescription.contents.translated
        )
    }

    private fun filterDescription(speciesDescription: SpeciesDescription) : String {
        try {
            log.info("filter species description by language : $language and version: $version")
            return speciesDescription.flavor_text_entries.filter { flavorText ->
                flavorText.language.name == language && flavorText.version.name == version
            }.first().flavor_text
        }catch (e: Throwable){
            throw NotFoundException("Cannot find pokemon species description for language: $language and version : $version")
        }
    }

}