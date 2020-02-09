package service

import com.project.pokemonspeare.exception.PokemonApiException
import com.project.pokemonspeare.exception.PokemonApiSpeciesException
import com.project.pokemonspeare.model.SpeciesDescription
import com.project.pokemonspeare.service.PokemonApiServiceImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`

import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.util.ReflectionTestUtils
import org.springframework.web.client.RestTemplate

@RunWith(SpringJUnit4ClassRunner::class)
@TestPropertySource(properties = [
    "pokemon.api.url=/urltest"
])
class PokemonApiServiceTest {

    @Mock
    private lateinit var restTemplate: RestTemplate
    @InjectMocks
    private lateinit var pokemonApiService: PokemonApiServiceImpl

    @Value("\${pokemon.api.url}")
    private lateinit var pokemonApiUrl: String

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        ReflectionTestUtils.setField(pokemonApiService, "pokemonApiUrl", pokemonApiUrl)
    }

    @Test(expected = PokemonApiException::class)
    fun testExceptionRetrievePokemon() {
        `when`(restTemplate.exchange(pokemonApiUrl, HttpMethod.GET, HttpEntity("header"), SpeciesDescription::class.java)).thenReturn(any())
        pokemonApiService.retrievePokemon("wolverine")
    }

    @Test(expected = PokemonApiSpeciesException::class)
    fun testExceptionRetrieveSpeciesPokemon() {
        `when`(restTemplate.exchange(pokemonApiUrl, HttpMethod.GET, HttpEntity("header"), SpeciesDescription::class.java)).thenReturn(any())
        pokemonApiService.retrieveSpeciesDescription("path/species/wolverine")
    }

}