package com.gabriel.wayairlinesapp.presenter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.wayairlinesapp.databinding.FlightItemBinding
import com.gabriel.wayairlinesapp.domain.model.Flight

class FlightAdapter(
    private val flights: List<Flight>,
    private val flightClick: (Int) -> Unit
): RecyclerView.Adapter<FlightAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: FlightItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            FlightItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount() = flights.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val flight = flights[position]

        holder.binding.textAirplaneName.text = flight.airplaneName
        holder.binding.textDepartureAirport.text = flight.departureAirport
        holder.binding.textArrivalAirport.text = flight.arrivalAirport
        holder.binding.textStatus.text = flight.status
        holder.binding.completionStatus.text = flight.completionStatus

        holder.itemView.setOnClickListener { flightClick }


    }
}