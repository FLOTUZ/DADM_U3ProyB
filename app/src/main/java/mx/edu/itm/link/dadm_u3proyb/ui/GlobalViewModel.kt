package mx.edu.itm.link.dadm_u3proyb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GlobalViewModel : ViewModel() {

    private val lat = MutableLiveData<Double>()
    private var lng = MutableLiveData<Double>()

    val getLat: LiveData<Double> get() = lat
    val getLng: LiveData<Double> get() = lng

    fun setLat(latitude: Double) {
        lat.value = latitude

    }

    fun setLng(longitude: Double) {
        lng.value = longitude
    }

}