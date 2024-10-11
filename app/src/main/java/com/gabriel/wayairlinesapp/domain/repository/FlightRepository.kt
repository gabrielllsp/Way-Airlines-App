package com.gabriel.wayairlinesapp.domain.repository

import com.gabriel.wayairlinesapp.data.model.FlightDTO
import com.gabriel.wayairlinesapp.data.model.FlightsResponse

interface FlightRepository {

    suspend fun getFlights(): FlightsResponse

    suspend fun getFlightsById(flightId: String): FlightDTO
}