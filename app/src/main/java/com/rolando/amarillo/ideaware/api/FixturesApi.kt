package com.rolando.amarillo.ideaware.api

import com.rolando.amarillo.ideaware.api.responses.FixtureResponse
import retrofit2.Call
import retrofit2.http.GET

interface FixturesApi {

    @GET("fixtures.json")
    fun getFixtures(): Call<List<FixtureResponse>>

}