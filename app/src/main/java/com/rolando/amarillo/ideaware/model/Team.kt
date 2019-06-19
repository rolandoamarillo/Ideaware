package com.rolando.amarillo.ideaware.model

import com.rolando.amarillo.ideaware.api.responses.TeamResponse

data class Team(
    val id: String,
    val name: String,
    val shortName: String,
    val abbr: String,
    val alias: String
) {

    companion object {
        fun parse(teamResponse: TeamResponse): Team {
            return Team(
                teamResponse.id,
                teamResponse.name,
                teamResponse.shortName,
                teamResponse.abbr,
                teamResponse.alias
            )
        }
    }

}