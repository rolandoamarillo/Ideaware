package com.rolando.amarillo.ideaware.model

import com.rolando.amarillo.ideaware.api.responses.ScoreResponse

data class Score(val home: Int, val away: Int, val winner: String?) {
    companion object {

        private const val HOME_WINNER = "home"
        private const val AWAY_WINNER = "away"

        fun parse(scoreResponse: ScoreResponse): Score {
            return Score(scoreResponse.home, scoreResponse.away, scoreResponse.winner)
        }
    }

    fun isHomeWinner(): Boolean {
        winner?.let {
            return winner.equals(HOME_WINNER, true)
        }
        return false
    }

    fun isAwayWinner(): Boolean {
        winner?.let {
            return winner.equals(AWAY_WINNER, true)
        }
        return false
    }
}