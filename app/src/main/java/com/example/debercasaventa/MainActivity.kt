package com.example.debercasaventa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val id = 10
    val textoNombre = "test"
    val textoApellido = "testA"
    val textoEmail = "testE"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_registro.setOnClickListener {
            this.irActividadRegistro()
        }

        button_usuarios.setOnClickListener {
            this.irActividadUsuarios()
        }

    }

    fun irActividadRegistro() {
        val intent = Intent(this, RegistroActivity::class.java)
        val usuario_pasar = Usuario(id, textoNombre, textoApellido, textoEmail)
        Log.i("usuario-pasar", usuario_pasar.nombre)
        intent.putExtra("usuario_pasar",usuario_pasar)
        startActivity(intent)
    }

    fun irActividadUsuarios(){
        val intent = Intent(this, UsuariosActivity::class.java)
        startActivity(intent)
    }


}
