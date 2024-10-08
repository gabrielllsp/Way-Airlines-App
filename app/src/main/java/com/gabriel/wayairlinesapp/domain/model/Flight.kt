package com.gabriel.wayairlinesapp.domain.model

data class Flight(
    val flightId: Int,
    val status: String,
    val completionStatus: String,
    val startDate: String,
    val endDate: String,
    val departureTime: String,
    val arrivalTime: String,
    val departureAirport: String,
    val arrivalAirport: String,
    val airplaneName: String
)