package com.project.pokemonspeare.service

import com.project.pokemonspeare.exception.ShakespeareApiException
import com.project.pokemonspeare.model.ShakespeareDescription
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import javax.inject.Inject
import org.springframework.http.*
import org.springframework.http.HttpEntity
import java.util.HashMap

@Service
class ShakespeareServiceImpl : ShakespeareService{

    @Inject private lateinit var restTemplate: RestTemplate
    @Value("\${shakespeare.api.url}") private lateinit var shakespeareApiUrl: String
    @Value("\${shakespeare.api.token}") private lateinit var token: String

    private val log : Logger = LoggerFactory.getLogger(ShakespeareServiceImpl::class.java)

    override fun translatePokemonDescription(description: String) : ShakespeareDescription{
        return try {
            log.info("--- Translate pokemon description : <$description> ---")
            val headers = HttpHeaders()

            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
            headers.contentType = MediaType.APPLICATION_JSON
            headers.add("X-FunTranslations-Api-Secret", token)

            val map = HashMap<String, String>()
            map["text"] = description

            val entity = HttpEntity<Map<String, Any>>(map, headers)
            restTemplate.postForObject("$shakespeareApiUrl/shakespeare", entity, ShakespeareDescription::class.java)!!
        } catch (exc: Throwable) {
                throw ShakespeareApiException("Could not translate pokemon description: $description")
            }
        }
    }