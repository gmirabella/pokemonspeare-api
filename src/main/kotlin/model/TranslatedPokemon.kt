package com.project.pokemonspeare.model

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

data class TranslatedPokemon(
        val name: String,
        val description: String)


class TranslatedPokemonRowMapper : RowMapper<TranslatedPokemon> {

    override fun mapRow(rs: ResultSet, rowNum: Int): TranslatedPokemon? {
        return TranslatedPokemon(
                name                  = rs.getString("name"),
                description           = rs.getString("description")
        )
    }
}
