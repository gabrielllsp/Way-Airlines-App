package com.gabriel.wayairlinesapp.data.model

import com.google.gson.annotations.SerializedName

data class FlightDTO(

    @SerializedName("flight_id")
    val flightId: String?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("completion_status")
    val completionStatus: String?,

    @SerializedName("start_date")
    val startDate: String?,

    @SerializedName("end_date")
    val endDate: String?,

    @SerializedName("departure_time")
    val departureTime: String?,

    @SerializedName("arrival_time")
    val arrivalTime: String?,

    @SerializedName("departure_airport")
    val departureAirport: String?,

    @SerializedName("arrival_airport")
    val arrivalAirport: String?,

    @SerializedName("airplane_name")
    val airplaneName: String?
)
