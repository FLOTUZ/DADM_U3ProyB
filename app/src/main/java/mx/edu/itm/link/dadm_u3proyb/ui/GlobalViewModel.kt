package mx.edu.itm.link.dadm_u3proyb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GlobalViewModel : ViewModel() {

    private val lat = MutableLiveData<Double>()
    private var lng = MutableLiveData<Double>()

    private val latNegocio = MutableLiveData<Double>()
    private var lngNegocio = MutableLiveData<Double>()

    val getLat: LiveData<Double> get() = lat
    val getLng: LiveData<Double> get() = lng

    val getLatN: LiveData<Double> get() = latNegocio
    val getLngN: LiveData<Double> get() = lngNegocio

    // Setters coordenadas del negocio
    fun setLatNegocio(latitude: Double) {
        latNegocio.value = latitude
    }

    fun setLngNegocio(longitude: Double) {
        lngNegocio.value = longitude
    }

    // Setters de coordenadas del dispositivo
    fun setLat(latitude: Double) {
        lat.value = latitude
    }

    fun setLng(longitude: Double) {
        lng.value = longitude
    }

}