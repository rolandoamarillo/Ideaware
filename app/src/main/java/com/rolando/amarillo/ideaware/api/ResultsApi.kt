package com.rolando.amarillo.ideaware.api

import com.rolando.amarillo.ideaware.api.responses.ResultResponse
import retrofit2.Call
import retrofit2.http.GET

interface ResultsApi {

    @GET("results.json")
    fun getResults(): Call<List<ResultResponse>>

}