package com.gabriel.wayairlinesapp.presenter.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gabriel.wayairlinesapp.domain.usecase.GetFlightByIdUseCase
import com.gabriel.wayairlinesapp.domain.usecase.GetFlightUseCase
import com.gabriel.wayairlinesapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class FlightDetailsViewModel @Inject constructor(
    private val getFlightByIdUseCase: GetFlightByIdUseCase
) : ViewModel() {



    fun getFlightsById() = liveData(Dispatchers.IO) {

        try {
            emit(StateView.Loading())

            val flight = getFlightByIdUseCase.invoke()

            emit(StateView.Success(flight))

        } catch (ex: HttpException) {
            ex.printStackTrace()

            emit(StateView.Error(message = ex.message()))

        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(StateView.Error(message = ex.message))
        }
    }
}