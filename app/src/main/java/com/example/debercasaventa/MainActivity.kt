package com.example.debercasaventa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
        startActivity(intent)
    }

    fun irActividadUsuarios(){
        val intent = Intent(this, UsuariosActivity::class.java)
        startActivity(intent)
    }


}
