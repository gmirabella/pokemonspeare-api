package service

import com.project.pokemonspeare.dao.PokemonShakespeareDao
import com.project.pokemonspeare.service.*
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.util.ReflectionTestUtils
import samples.PokemonSamples
import samples.ShakespeareDescriptionSamples
import samples.SpeciesDescriptionSamples
import samples.TranslatedPokemonSamples
import java.time.Instant
import kotlin.test.assertEquals


@RunWith(SpringJUnit4ClassRunner::class)
@TestPropertySource(properties = [
    "pokemon.description.language=en",
    "pokemon.description.version=version-1"
])
class PokemonShakespeareServiceTest{

    @Mock private lateinit var pokemonShakespeareDao: PokemonShakespeareDao
    @Mock private lateinit var pokemonApiService: PokemonApiService
    @Mock private lateinit var shakespeareService: ShakespeareService
    @InjectMocks private lateinit var pokemonShakespeareService: PokemonShakespeareServiceImpl

    @Value("\${pokemon.description.language}") private lateinit var language: String
    @Value("\${pokemon.description.version}") private lateinit var version: String

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        ReflectionTestUtils.setField(pokemonShakespeareService, "language", language)
        ReflectionTestUtils.setField(pokemonShakespeareService, "version", version)
    }


    @Test
    fun testGetExistingPokemon(){
        `when`(pokemonShakespeareDao.getPokemonBackuped("bulbasaur")).thenReturn(TranslatedPokemonSamples.bulbasaur)
        val result = pokemonShakespeareService.getTranslatedPokemon("bulbasaur")

        assertNotNull(result)
        assertEquals(result, TranslatedPokemonSamples.bulbasaur)
    }

    @Test
    fun testGetNotAlreadyExistingPokemon(){
        `when`(pokemonShakespeareDao.getPokemonBackuped("charmander")).thenReturn(null)
        `when`(pokemonApiService.retrievePokemon("charmander")).thenReturn(PokemonSamples.charmander)
        `when`(pokemonApiService.retrieveSpeciesDescription(PokemonSamples.charmander.species.url)).thenReturn(SpeciesDescriptionSamples.charmanderDescription)
        `when`(shakespeareService.translatePokemonDescription("test")).thenReturn(ShakespeareDescriptionSamples.charmanderTransl)
        `when`(pokemonShakespeareDao.savePokemonBackuped(TranslatedPokemonSamples.charmander.name, ShakespeareDescriptionSamples.charmanderTransl, Instant.now())).thenReturn(true)

        val result = pokemonShakespeareService.getTranslatedPokemon("charmander")

        assertNotNull(result)
        assertEquals(result, TranslatedPokemonSamples.charmander)
    }


}