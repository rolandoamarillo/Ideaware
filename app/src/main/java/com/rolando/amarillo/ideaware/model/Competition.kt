package com.rolando.amarillo.ideaware.model

import com.rolando.amarillo.ideaware.api.responses.CompetitionResponse

data class Competition(val id: String, val name: String) {

    companion object {
        fun parse(competitionResponse: CompetitionResponse): Competition {
            return Competition(competitionResponse.id, competitionResponse.name)
        }
    }

}
