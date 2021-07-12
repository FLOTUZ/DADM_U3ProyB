package mx.edu.itm.link.dadm_u3proyb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.itm.link.dadm_u3proyb.models.Pedido

class GlobalViewModel : ViewModel() {

    val lat = MutableLiveData<Double>()
    var lng = MutableLiveData<Double>()

    val latNegocio = MutableLiveData<Double>()
    val lngNegocio = MutableLiveData<Double>()

    val pedidoActual = MutableLiveData<Pedido>()

    val listaPedidos = MutableLiveData<ArrayList<Pedido>>()

    // Setters coordenadas del negocio
    fun setLatNegocio(lat: Double) {
        latNegocio.value = lat
    }

    fun setLngNegocio(lng: Double) {
        lngNegocio.value = lng
    }

    // Setters de coordenadas del dispositivo
    fun setLat(latitude: Double) {
        lat.value = latitude
    }

    fun setLng(longitude: Double) {
        lng.value = longitude
    }

    fun setPedidoActual(pedido: Pedido) {
        pedidoActual.value = pedido
    }

    fun addPedidoALista(lista: ArrayList<Pedido>) {
        listaPedidos.value = lista
    }

}