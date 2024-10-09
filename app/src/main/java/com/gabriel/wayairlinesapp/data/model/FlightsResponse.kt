package com.gabriel.wayairlinesapp.data.model

import com.google.gson.annotations.SerializedName

data class FlightsResponse(
    @SerializedName("flights")
    val flights: List<FlightDTO>?
)
