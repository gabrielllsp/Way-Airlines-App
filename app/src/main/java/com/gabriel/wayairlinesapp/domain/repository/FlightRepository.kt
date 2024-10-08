package com.gabriel.wayairlinesapp.domain.repository

import com.gabriel.wayairlinesapp.data.model.FlightsResponse

interface FlightRepository {

    suspend fun getFlights(): FlightsResponse
}