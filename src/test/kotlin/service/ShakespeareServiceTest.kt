package service

import com.project.pokemonspeare.exception.PokemonApiException
import com.project.pokemonspeare.exception.ShakespeareApiException
import com.project.pokemonspeare.model.SpeciesDescription
import com.project.pokemonspeare.service.PokemonApiServiceImpl
import com.project.pokemonspeare.service.PokemonShakespeareService
import com.project.pokemonspeare.service.ShakespeareService
import com.project.pokemonspeare.service.ShakespeareServiceImpl
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.test.context.TestPropertySource
import org.springframework.test.util.ReflectionTestUtils
import org.springframework.web.client.RestTemplate


@TestPropertySource(properties = [
    "shakespeare.api.url=/urltest","shakespeare.api.token=token"
])
class ShakespeareServiceTest {

    @Mock private lateinit var restTemplate: RestTemplate
    @InjectMocks private lateinit var shakespeareService: ShakespeareServiceImpl

    @Value("\${shakespeare.api.url}")
    private lateinit var shakespeareApiUrl: String

    @Value("\${shakespeare.api.token}")
    private lateinit var shakespeareApiToken: String

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        ReflectionTestUtils.setField(shakespeareService, "shakespeareApiUrl", shakespeareApiUrl)
        ReflectionTestUtils.setField(shakespeareService, "shakespeareApiToken", shakespeareApiToken)
    }


    @Test(expected = ShakespeareApiException::class)
    fun testExceptionRetrievePokemon() {
        Mockito.`when`(restTemplate.exchange(shakespeareApiUrl, HttpMethod.GET, HttpEntity("header"), SpeciesDescription::class.java)).thenReturn(ArgumentMatchers.any())
        shakespeareService.translatePokemonDescription("desc error")
    }

}