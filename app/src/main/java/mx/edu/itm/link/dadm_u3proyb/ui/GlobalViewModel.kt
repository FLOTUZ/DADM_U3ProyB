package mx.edu.itm.link.dadm_u3proyb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.itm.link.dadm_u3proyb.models.Pedido

class GlobalViewModel : ViewModel() {

    private val lat = MutableLiveData<Double>()
    private var lng = MutableLiveData<Double>()

    private val latNegocio = MutableLiveData<Double>()
    private val lngNegocio = MutableLiveData<Double>()

    private val pedidoActual = MutableLiveData<Pedido>()
    private val listaPedidos = MutableLiveData<ArrayList<Pedido>>()

    val getLat: LiveData<Double> get() = lat
    val getLng: LiveData<Double> get() = lng

    val getLatNegocio: LiveData<Double> get() = latNegocio
    val getlngNegocio: LiveData<Double> get() = lngNegocio

    val getPedido: LiveData<Pedido> get() = pedidoActual
    val getListaPedidos: LiveData<ArrayList<Pedido>> get() = listaPedidos

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