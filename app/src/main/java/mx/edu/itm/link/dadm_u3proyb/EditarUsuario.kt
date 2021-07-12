package mx.edu.itm.link.dadm_u3proyb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import mx.edu.itm.link.dadm_u3proyb.databinding.ActivityEditarUsuarioBinding
import mx.edu.itm.link.dadm_u3proyb.models.Usuario
import mx.edu.itm.link.dadm_u3proyb.ui.GlobalViewModel
import mx.edu.itm.link.dadm_u3proyb.utils.MyUtils
import org.json.JSONObject
import www.sanju.motiontoast.MotionToast

class EditarUsuario : AppCompatActivity() {
    private lateinit var binding: ActivityEditarUsuarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_usuario)
        binding = ActivityEditarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener {
            val nombre = binding.tfNombre.text.toString()
            val telefono = binding.tfTelefono.text.toString()
            val email = binding.tfEmail.text.toString()
            val password = binding.tfPassword.text.toString()

            val url = "${resources.getString(R.string.apiNode)}actualizarUsuario"

            val params = HashMap<String, String>()
            params.put("id", MainActivity.usuarioLogueado.id.toString())
            params.put("nombre", nombre)
            params.put("telefono", telefono)
            params.put("email", email)
            params.put("password", password)
            object : MyUtils() {
                override fun formatResponse(response: String) {
                    val respuesta = JSONObject(response)
                    val code = respuesta.getInt("code")

                    Log.d("EDITARUSUARIO", response)
                    if (code == 200) {
                        MotionToast.createToast(
                            this@EditarUsuario,
                            "Exito ☺",
                            "Se actualizo el usuario ${nombre}",
                            MotionToast.TOAST_SUCCESS,
                            MotionToast.GRAVITY_CENTER,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(this@EditarUsuario, R.font.helvetica_regular)
                        )
                        MainActivity.usuarioLogueado =
                            Usuario(MainActivity.usuarioLogueado.id, email, password, nombre, telefono)
                        finish()
                    } else {
                        MotionToast.createToast(
                            this@EditarUsuario,
                            "Error :(",
                            "Verifica tu conexion a internet",
                            MotionToast.TOAST_ERROR,
                            MotionToast.GRAVITY_CENTER,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(this@EditarUsuario, R.font.helvetica_regular)
                        )
                    }
                }
            }.consumePost(this, url, params)
        }

        binding.btnCancelar.setOnClickListener {
            finish()
        }
    }
}