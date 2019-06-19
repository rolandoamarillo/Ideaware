package com.rolando.amarillo.ideaware.model

import com.rolando.amarillo.ideaware.api.responses.ResultResponse

class Result(
    id: String,
    type: String,
    date: String,
    state: String,
    homeTeam: Team,
    awayTeam: Team,
    competitionStage: CompetitionStage,
    venue: Venue,
    val score: Score
) : Fixture(id, type, date, state, homeTeam, awayTeam, competitionStage, venue) {
    companion object {
        fun parse(resultResponse: ResultResponse): Result {
            return Result(
                resultResponse.id,
                resultResponse.type,
                resultResponse.date,
                resultResponse.state,
                Team.parse(resultResponse.homeTeam),
                Team.parse(resultResponse.awayTeam),
                CompetitionStage.parse(resultResponse.competitionStage),
                Venue.parse(resultResponse.venue),
                Score.parse(resultResponse.score)
            )
        }
    }
}