package com.rolando.amarillo.ideaware.model

import com.rolando.amarillo.ideaware.api.responses.CompetitionStageResponse

data class CompetitionStage(val competition: Competition) {

    companion object {
        fun parse(competitionStageResponse: CompetitionStageResponse): CompetitionStage {
            return CompetitionStage(Competition.parse(competitionStageResponse.competition))
        }
    }

}