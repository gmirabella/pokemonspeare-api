package com.project.pokemonspeare.dao

import com.project.pokemonspeare.model.ShakespeareDescription
import com.project.pokemonspeare.model.TranslatedPokemon
import com.project.pokemonspeare.model.TranslatedPokemonRowMapper
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

    override fun savePokemonBackuped(name: String, shakespeareDescription: ShakespeareDescription, createdOn: Instant) : Boolean {
        log.info("--- Saving new pokemon with name: <$name> ---")
        val sqlQuery = """
            insert into pokemon (
                name,
                description,
                created_on
            ) values (?,?,?)
        """.trimIndent()

        val statement = PreparedStatementCreator { connection ->
            connection.prepareStatement(sqlQuery).apply {
                setString(1, name)
                setString(2, shakespeareDescription.contents.translated)
                setTimestamp(3, Timestamp.from(createdOn))
            }
        }

        return jdbcTemplate.update(statement) == 1

    }

    override fun getPokemonBackuped(name: String) : TranslatedPokemon? {
        log.info("--- Getting existing Pokemon with name: <$name> ---")
        val sqlQuery = "SELECT * FROM pokemon WHERE name = ?"

        val statement = PreparedStatementCreator { con ->
            con.prepareStatement(sqlQuery).apply {
                setString(1, name)
            }
        }
        return jdbcTemplate.query(statement, TranslatedPokemonRowMapper()).firstOrNull()
    }

}