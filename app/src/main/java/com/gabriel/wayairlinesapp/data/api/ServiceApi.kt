package com.gabriel.wayairlinesapp.data.api

import com.gabriel.wayairlinesapp.data.model.FlightsResponse
import io.github.brunogabriel.mockpinterceptor.MOCK
import retrofit2.http.GET

interface ServiceApi {

    @MOCK(asset = "flights_response.json", runDelay = true)
    @GET("rankings.json")
    suspend fun getFlights(): FlightsResponse
}