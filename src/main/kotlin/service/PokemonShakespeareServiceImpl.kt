package com.project.pokemonspeare.service

import com.project.pokemonspeare.controller.PokemonShakespeareControllerImpl
import com.project.pokemonspeare.dao.PokemonShakespeareDao
import com.project.pokemonspeare.model.Pokemon
import com.project.pokemonspeare.model.TranslatedPokemon
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.inject.Inject

@Service
class PokemonShakespeareServiceImpl : PokemonShakespeareService {

    @Inject private lateinit var pokemonService: PokemonService
    @Inject private lateinit var shakespeareService: ShakespeareService
    @Inject private lateinit var pokemonShakespeareDao: PokemonShakespeareDao


    private val log : Logger = LoggerFactory.getLogger(PokemonShakespeareControllerImpl::class.java)

    override fun getTranslatedPokemon(name: String): TranslatedPokemon {

        //val pokemon = pokemonShakespeareDao.getPokemonBackuped(name).let { pokemonService.retrievePokemon(name) }
         val pokemon = pokemonService.retrievePokemon(name)
        //shakespeareService.translatePokemonDescription(extractPokemonDescription(pokemon!!))

        //pokemonShakespeareDao.savePokemonBackuped(pokemon.name, )


        return TranslatedPokemon("prova", "prova")

    }

    private fun extractPokemonDescription(pokemon: Pokemon): String{
        return pokemon.species.url

    }
}