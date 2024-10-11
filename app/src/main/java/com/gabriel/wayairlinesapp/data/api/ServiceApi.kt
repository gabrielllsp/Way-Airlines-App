package com.gabriel.wayairlinesapp.data.api

import com.gabriel.wayairlinesapp.data.model.FlightDTO
import com.gabriel.wayairlinesapp.data.model.FlightsResponse
import io.github.brunogabriel.mockpinterceptor.MOCK
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {

    @MOCK(asset = "flights_response.json", runDelay = true)
    @GET("rankings.json")
    suspend fun getFlights(): FlightsResponse

    @MOCK(asset = "flights_response.json", runDelay = true)
    @GET("rankings.json/{flightId}")
    suspend fun getFlightsById(
        @Path("flightId") flightId: String
    ): FlightDTO
}