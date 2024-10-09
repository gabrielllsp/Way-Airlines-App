package com.gabriel.wayairlinesapp.data.mapper

import com.gabriel.wayairlinesapp.data.model.FlightDTO
import com.gabriel.wayairlinesapp.domain.model.Flight

fun FlightDTO.toDomain() = Flight(
    flightId = this.flightId,
    status = this.status,
    completionStatus = this.completionStatus,
    startDate = this.startDate,
    endDate = this.endDate,
    departureTime = this.departureTime,
    arrivalTime = this.arrivalTime,
    departureAirport = this.departureAirport,
    arrivalAirport = this.arrivalAirport,
    airplaneName = this.airplaneName
)