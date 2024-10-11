package com.gabriel.wayairlinesapp.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.wayairlinesapp.databinding.FlightItemBinding
import com.gabriel.wayairlinesapp.domain.model.Flight

class FlightAdapter(

    private var flights: List<Flight>,
    private val flightClick: (String) -> Unit
) : RecyclerView.Adapter<FlightAdapter.MyViewHolder>() {

    private var filteredItems: List<Flight> = flights


    inner class MyViewHolder(val binding: FlightItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            FlightItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount() = filteredItems.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val flight = filteredItems[position]

        holder.binding.textAirplaneName.text = flight.airplaneName
        holder.binding.textDepartureAirport.text = flight.departureAirport
        holder.binding.textArrivalAirport.text = flight.arrivalAirport
        holder.binding.textStatus.text = flight.status
        holder.binding.completionStatus.text = flight.completionStatus

        holder.itemView.setOnClickListener {
            flight.flightId?.let { it1 -> flightClick(it1) }
        }

    }
}