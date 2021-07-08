package mx.edu.itm.link.dadm_u3proyb.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itm.link.dadm_u3proyb.R
import mx.edu.itm.link.dadm_u3proyb.adapters.CommerceAdapter
import mx.edu.itm.link.dadm_u3proyb.models.Negocio
import mx.edu.itm.link.dadm_u3proyb.utils.MyUtils
import org.json.JSONObject

class HomeFragment : Fragment() {

    private lateinit var url : String
    private lateinit var recyclerNegocios: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editSearch = view.findViewById<EditText>(R.id.editSearch)

        recyclerNegocios = view.findViewById(R.id.recyclerCommerces)

        url = resources.getString(R.string.api)+"comercios.php"

        object : MyUtils(){
            override fun formatResponse(response: String) {
                try {
                    val json = JSONObject(response)
                    val output = json.getJSONArray("output")

                    val negocios = ArrayList<Negocio>()

                    for(i in 0..output.length()-1) {
                        val jsonCommerce = output.getJSONObject(i)
                        val negocio = Negocio(
                            jsonCommerce.getInt("id"),
                            jsonCommerce.getString("negocio"),
                            jsonCommerce.getString("descripcion"),
                            jsonCommerce.getString("direccion"),
                            jsonCommerce.getDouble("latitud"),
                            jsonCommerce.getDouble("longitud"),
                            jsonCommerce.getInt("id_categoria"),
                            jsonCommerce.getString("categoria"),
                            if(jsonCommerce.getInt("favorito")==1) true else false,
                            jsonCommerce.getString("foto")
                        )

                        negocios.add(negocio)

                        //Se crea la lista con el Recycler (Lista completa)
                        actualizarLista(view, negocios)
                    }

                    editSearch.doOnTextChanged { text, start, before, count ->
                        val listaFiltrada = negocios.filter { n ->
                            n.commerce.contains(text.toString(), ignoreCase = true) ||
                            n.description.contains(text.toString(), ignoreCase = true) ||
                            n.category.contains(text.toString(), ignoreCase = true)
                        }
                        Log.d("LISTAFILTRADA", listaFiltrada.toString())
                        actualizarLista(view, listaFiltrada as ArrayList<Negocio>)
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    "Error, no hay negocios disponibles".toast(view.context)
                }
            }
        }.consumeGet(view.context, url)
    }

    fun actualizarLista(view: View, negocios: ArrayList<Negocio>) {
        recyclerNegocios.adapter = CommerceAdapter(view.context, R.layout.recycler_row_commerce, negocios)
        recyclerNegocios.layoutManager = LinearLayoutManager(view.context)
    }

}