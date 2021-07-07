package mx.edu.itm.link.dadm_u3proyb.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import mx.edu.itm.link.dadm_u3proyb.R
import mx.edu.itm.link.dadm_u3proyb.ui.GlobalViewModel

class DashboardFragment : Fragment() {

    private val viewModel: GlobalViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        viewModel.getLatN.observe(viewLifecycleOwner, Observer {
            Log.d("LATNEGOCIO", "LAT NEGOCIO: $it")
        })

        viewModel.getLngN.observe(viewLifecycleOwner, Observer {
            Log.d("LATNEGOCIO", "LNG NEGOCIO: $it")
        })

        childFragmentManager.findFragmentById(R.id.map)?.let {
            val map = it as SupportMapFragment

            /*
            * latD = Latitud del dispositivo
            * lngD = longitud del dispositivo
            * latN = Latitud del negocio
            * lngN = longitud del negocio
            */
            viewModel.getLng.observe(viewLifecycleOwner, { lngD ->
                viewModel.getLat.observe(viewLifecycleOwner, { latD ->

                    if (latD != 0.0 && lngD != 0.0) {
                        map.getMapAsync { map ->
                            val local = LatLng(latD, lngD)
                            val negocio = LatLng(19.7229386, -101.1858201)
                            map.addMarker(
                                MarkerOptions().position(local).title("Tu ubicacion")
                            )
                            map.addMarker(
                                MarkerOptions().position(negocio).title("Tu pedido")
                                    .icon(
                                        BitmapDescriptorFactory.defaultMarker(
                                            BitmapDescriptorFactory.HUE_GREEN
                                        )
                                    )
                            )
                            map.moveCamera(CameraUpdateFactory.newLatLng(local))
                            map.animateCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    LatLng(
                                        latD,
                                        lngD
                                    ), 13.0f
                                )
                            )
                        }
                    }
                })
            })


        }

        return root
    }


}