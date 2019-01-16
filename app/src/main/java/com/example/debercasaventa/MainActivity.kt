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
    }

    fun irActividadRegistro() {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }


}
