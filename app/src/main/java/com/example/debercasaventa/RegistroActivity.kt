package com.example.debercasaventa

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        button_guardar_usuario.setOnClickListener {
            guardarUsuario()
        }


    }

    fun guardarUsuario() {
        val textoNombre = editText_nombre_usuario.text
        val textoApellido = editText_apellido_usuario.text
        val textoEmail = editText_email_usuario.text
        Log.i("bdd", "Vamos a guardar los datos")

        // Verificar si ya existe los datos


        val helper = SqliteHelper(applicationContext)

//        val noExisteRegistroDeUsuario = helper.existeUsuarioFormulario().nombre == null


        val seCreo = helper
            .crearUsuarioFormulario(
                textoNombre.toString(),
                textoApellido.toString(),
                textoEmail.toString()
            )

        Log.i("bdd", "Se creo ${seCreo}")

//        } else {
//            helper
//                .actualizarUsuarioFormulario(
//                    textoNombre.toString(),
//                    textoDescripcion.toString()
//                )
//        }
    }
}
