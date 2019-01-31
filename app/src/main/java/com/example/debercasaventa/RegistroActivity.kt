package com.example.debercasaventa

import android.content.Intent
import android.database.Cursor
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val usuario = intent.getParcelableExtra<Usuario?>("usuario_pasar")

        if (usuario != null) {
            Log.i("intent-usuario", usuario.nombre)
        }

        button_guardar_usuario.setOnClickListener {
            guardarUsuario()
            this.finish()
        }

        val texto: String? = intent.getStringExtra(Intent.EXTRA_TEXT)
        Log.i("intent-texto", "Texto: ${texto}")

        if(texto != null){
            editText_email_usuario.setText(texto)
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
