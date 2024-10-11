package com.gabriel.wayairlinesapp.data.repository

import com.gabriel.wayairlinesapp.data.api.ServiceApi
import com.gabriel.wayairlinesapp.data.model.FlightDTO
import com.gabriel.wayairlinesapp.data.model.FlightsResponse
import com.gabriel.wayairlinesapp.domain.repository.FlightRepository
import javax.inject.Inject

class FlightRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
) : FlightRepository {
    override suspend fun getFlights(): FlightsResponse {
        return serviceApi.getFlights()
    }

    override suspend fun getFlightsById(flightId: String): FlightDTO {
        return serviceApi.getFlightsById(flightId)
    }
}