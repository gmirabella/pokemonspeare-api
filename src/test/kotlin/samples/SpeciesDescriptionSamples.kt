package samples

import com.project.pokemonspeare.model.Description
import com.project.pokemonspeare.model.Language
import com.project.pokemonspeare.model.SpeciesDescription
import com.project.pokemonspeare.model.Version

object SpeciesDescriptionSamples{

    val charmanderDescription = SpeciesDescription(
            flavor_text_entries = listOf(Description(
                    flavor_text = "test",
                    language = Language("en"),
                    version = Version("version-1")

            ))
    )
}