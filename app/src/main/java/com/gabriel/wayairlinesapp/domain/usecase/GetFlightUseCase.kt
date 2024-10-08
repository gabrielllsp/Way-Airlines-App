package com.gabriel.wayairlinesapp.domain.usecase

import com.gabriel.wayairlinesapp.data.mapper.toDomain
import com.gabriel.wayairlinesapp.domain.model.Flight
import com.gabriel.wayairlinesapp.domain.repository.FlightRepository
import javax.inject.Inject

class GetFlightUseCase @Inject constructor(
    private val flightRepository: FlightRepository
) {
    suspend operator fun invoke(): List<Flight> {
        return flightRepository.getFlights().flights.map { it.toDomain() }
    }
}