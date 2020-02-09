package com.project.pokemonspeare.dao

import com.project.pokemonspeare.model.DBPokemon
import com.project.pokemonspeare.model.DBPokemonRowMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.time.Instant
import javax.inject.Inject

@Repository
class PokemonShakespeareDaoImpl : PokemonShakespeareDao{

    @Inject private lateinit var jdbcTemplate: JdbcTemplate

    private val log : Logger = LoggerFactory.getLogger(PokemonShakespeareDaoImpl::class.java)

    override fun savePokemonBackuped(name: String, description: String, translatedDescription: String, createdOn: Instant) : Boolean {
        log.info("--- Saving new pokemon with name: <$name> ---")
        val sqlQuery = """
            insert into pokemon (
                name,
                description,
                translated_description,
                created_on
            ) values (?,?,?,?)
        """.trimIndent()

        val statement = PreparedStatementCreator { connection ->
            connection.prepareStatement(sqlQuery).apply {
                setString(1, name)
                setString(2, description)
                setString(3, translatedDescription)
                setTimestamp(4, Timestamp.from(createdOn))
            }
        }

        return jdbcTemplate.update(statement) == 1

    }

    override fun getPokemonBackuped(name: String) : DBPokemon? {
        log.info("--- Getting existing Pokemon with name: <$name> ---")
        val sqlQuery = "SELECT * FROM pokemon WHERE name = ?"

        val statement = PreparedStatementCreator { con ->
            con.prepareStatement(sqlQuery).apply {
                setString(1, name)
            }
        }
        return jdbcTemplate.query(statement, DBPokemonRowMapper()).firstOrNull()
    }
}