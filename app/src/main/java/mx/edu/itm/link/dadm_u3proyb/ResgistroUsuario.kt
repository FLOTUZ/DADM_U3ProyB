package mx.edu.itm.link.dadm_u3proyb

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import mx.edu.itm.link.dadm_u3proyb.databinding.ActivityResgistrousuarioBinding
import mx.edu.itm.link.dadm_u3proyb.ui.GlobalViewModel
import mx.edu.itm.link.dadm_u3proyb.utils.MyUtils
import mx.edu.itm.link.dadm_u3proyb.utils.MyUtils.Companion.toast
import org.json.JSONObject
import www.sanju.motiontoast.MotionToast

class ResgistroUsuario : AppCompatActivity() {
    private val viewModel: GlobalViewModel by viewModels()   //Solo por si se ocupa
    private lateinit var binding: ActivityResgistrousuarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResgistrousuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConfirmarRegistro.setOnClickListener {
            val nombre = binding.tfNombreRegistro
            val email = binding.tfEmailRegistro
            val telefono = binding.tfTelefonoRegistro
            val password = binding.tfPasswordRegistro
            val passwordConfirmado = binding.tfPasswordConfirmadoRegistro

            var correcto = true
            if (nombre.text.toString().isEmpty()) {
                nombre.error = "El usuario no debe ser vacío"
                correcto = false
            }
            if (!email.text.toString().contains("@") || !email.text.toString().contains(".")
                || email.text.toString().length < 5
            ) {
                email.error = "El correo no es válid"
                correcto = false
            }
            if (telefono.text.toString().isEmpty()) {
                telefono.error = "El telefono no debe estar vacio"
                correcto = false
            }
            if (password.text.toString().isEmpty()) {
                password.error = "La contraseña no debe ser vacía"
                correcto = false
            }
            if (passwordConfirmado.text.toString().length < 2) {
                passwordConfirmado.error = "La contraseña es muy corta"
                correcto = false
            }
            if (password.text.toString() != passwordConfirmado.text.toString()) {
                passwordConfirmado.error = "Las contraseñas no coinciden"
                correcto = false
            }

            if (correcto) {

                val name = nombre.text.toString()
                val mail = email.text.toString()
                val phone = telefono.text.toString()
                val pass = password.text.toString()

                val url = "${resources.getString(R.string.apiNode)}altaUsuario"

                val params = HashMap<String, String>()
                params.put("nombre", name)
                params.put("telefono", phone)
                params.put("email", mail)
                params.put("password", pass)

                object : MyUtils() {
                    override fun formatResponse(response: String) {
                        val respuesta = JSONObject(response)
                        val code = respuesta.getInt("code")

                        if (code == 200) {
                            MotionToast.createToast(
                                this@ResgistroUsuario,
                                "Exito! >:)",
                                "Se registro con exito!",
                                MotionToast.TOAST_SUCCESS,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(
                                    this@ResgistroUsuario,
                                    R.font.helvetica_regular
                                )
                            )
                            finish()
                        } else {
                            MotionToast.createToast(
                                this@ResgistroUsuario,
                                "Error :(",
                                "Verifica tu conexion a internet o que el server este corriendo",
                                MotionToast.TOAST_ERROR,
                                MotionToast.GRAVITY_CENTER,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(
                                    this@ResgistroUsuario,
                                    R.font.helvetica_regular
                                )
                            )
                        }
                    }
                }.consumePost(this, url, params)
            } else {
                MotionToast.createToast(
                    this,
                    "ERROR!",
                    "Verifica ts datos",
                    MotionToast.TOAST_ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular)
                )
            }
        }

        binding.btnCancelarRegistro.setOnClickListener {
            finish()
        }

    }
}