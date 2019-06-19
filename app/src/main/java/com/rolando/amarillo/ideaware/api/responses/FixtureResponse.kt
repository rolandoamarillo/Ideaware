package com.rolando.amarillo.ideaware.api.responses

data class FixtureResponse(
    val id: String,
    val type: String,
    val date: String,
    val state: String?,
    val homeTeam: TeamResponse,
    val awayTeam: TeamResponse,
    val competitionStage: CompetitionStageResponse,
    val venue: VenueResponse
)