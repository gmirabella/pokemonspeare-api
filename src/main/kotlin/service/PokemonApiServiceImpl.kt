package com.project.pokemonspeare.service

import com.project.pokemonspeare.exception.PokemonApiException
import com.project.pokemonspeare.exception.PokemonApiSpeciesException
import com.project.pokemonspeare.model.Pokemon
import com.project.pokemonspeare.model.SpeciesDescription
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import javax.inject.Inject
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod


@Service
class PokemonApiServiceImpl : PokemonApiService {

    @Inject private lateinit var restTemplate: RestTemplate
    @Value("\${pokemon.api.url}") private lateinit var pokemonApiUrl: String

    private val log : Logger = LoggerFactory.getLogger(PokemonApiServiceImpl::class.java)

    override fun retrievePokemon(name: String): Pokemon {
        return try {
            log.info("--- retrieve pokemon with name : $name ---")

            val headers = HttpHeaders()
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
            val entity = HttpEntity("parameters", headers)
            restTemplate.exchange("$pokemonApiUrl/pokemon/$name", HttpMethod.GET, entity, Pokemon::class.java).body!!

        } catch (ex: Throwable) {
            throw PokemonApiException("Could not retrieve pokemon with name $name")
        }
    }


    override fun retrieveSpeciesDescription(url: String): SpeciesDescription {
        return try {
            log.info("--- retrieve species description: <$url> ---")

            val headers = HttpHeaders()
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
            val entity = HttpEntity("parameters", headers)
            restTemplate.exchange(url, HttpMethod.GET, entity, SpeciesDescription::class.java).body!!

        } catch (ex: Throwable) {
            throw PokemonApiSpeciesException("Could not retrieve species description from url $url")
        }
    }

}