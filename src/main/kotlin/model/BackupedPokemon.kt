package com.project.pokemonspeare.model

import java.sql.ResultSet
import org.springframework.jdbc.core.RowMapper

data class DBPokemon(
        val id: Long,
        val name: String,
        val description: String,
        val translatedDescription: String
)

class DBPokemonRowMapper : RowMapper<DBPokemon> {

    override fun mapRow(rs: ResultSet, rowNum: Int): DBPokemon? {
            return DBPokemon(
                    id                    = rs.getLong("id"),
                    name                  = rs.getString("name"),
                    description           = rs.getString("description"),
                    translatedDescription = rs.getString("translated_description")

            )
        }
    }
