package com.rolando.amarillo.ideaware.model

import com.rolando.amarillo.ideaware.api.responses.VenueResponse

data class Venue(val id: String, val name: String) {

    companion object {
        fun parse(venueResponse: VenueResponse): Venue {
            return Venue(venueResponse.id, venueResponse.name)
        }
    }

}