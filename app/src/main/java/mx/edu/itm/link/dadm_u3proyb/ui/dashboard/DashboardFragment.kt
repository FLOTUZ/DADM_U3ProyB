package mx.edu.itm.link.dadm_u3proyb.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
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

        childFragmentManager.findFragmentById(R.id.map)?.let {
            val map = it as SupportMapFragment

            viewModel.getLng.observe(viewLifecycleOwner, { lng ->
                viewModel.getLat.observe(viewLifecycleOwner, { lat ->
                    if (lat != 0.0 && lng != 0.0) {
                        map.getMapAsync { map ->
                            val current = LatLng(lat, lng)
                            map.addMarker(
                                MarkerOptions().position(current).title("Tu ubicacion")
                            )
                            map.moveCamera(CameraUpdateFactory.newLatLng(current))
                        }
                    }
                })
            })

        }

        return root
    }


}