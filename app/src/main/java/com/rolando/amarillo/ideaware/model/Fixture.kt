package com.rolando.amarillo.ideaware.model

import com.rolando.amarillo.ideaware.api.responses.FixtureResponse

open class Fixture(
    val id: String,
    val type: String,
    val date: String,
    private val state: String?,
    val homeTeam: Team,
    val awayTeam: Team,
    val competitionStage: CompetitionStage,
    val venue: Venue
) {
    companion object {

        private const val POSTPONED_STATE = "postponed"

        fun parse(fixtureResponse: FixtureResponse): Fixture {
            return Fixture(
                fixtureResponse.id,
                fixtureResponse.type,
                fixtureResponse.date,
                fixtureResponse.state,
                Team.parse(fixtureResponse.homeTeam),
                Team.parse(fixtureResponse.awayTeam),
                CompetitionStage.parse(fixtureResponse.competitionStage),
                Venue.parse(fixtureResponse.venue)
            )
        }
    }

    fun isPostponed(): Boolean {
        return POSTPONED_STATE.equals(state, true)
    }
}