package com.example.debercasaventa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_editar_usuario.*
import kotlinx.android.synthetic.main.activity_registro.*

class EditarUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_usuario)

/*
        val usuario = intent.getParcelableExtra<Usuario?>("usuario")

        if(usuario != null){
            Log.i("paso", "${usuario.nombre}, ${usuario.apellido}, ${usuario.email}, ${usuario.id}")
            mostrarDatos(usuario)
        }
*/
        val id = intent.getIntExtra("id_usuario", 1)


        button_regresar_usuario.setOnClickListener {
            this.irAActividadUsuarios()
        }

        button_actualizar_usuario.setOnClickListener {
            this.actualizarUsuario(id)
            this.irAActividadUsuarios()
        }

        button_eliminar_usuario.setOnClickListener {
            this.eliminarUsuario(id)
            this.irAActividadUsuarios()
        }

    }

    fun irAActividadUsuarios(){
        val intent = Intent(this, UsuariosActivity::class.java)
        startActivity(intent)
    }

    fun actualizarUsuario(id:Int){
        val id = id
        val textoNombre = actualizarText_usuario_nombre.text
        val textoApellido = actualizarText_usuario_apellido.text
        val textoEmail = actualizarText_usuario_email.text
        Log.i("bdd", "Vamos a guardar los datos")

        // Verificar si ya existe los datos


        val helper = SqliteHelper(applicationContext)

//        val noExisteRegistroDeUsuario = helper.existeUsuarioFormulario().nombre == null


        val seActualizo = helper
            .actualizarUsuarioFormulario(
                id,
                textoNombre.toString(),
                textoApellido.toString(),
                textoEmail.toString()
            )

        Log.i("bdd", "Se actualizo ${seActualizo}")
    }

    fun eliminarUsuario(id:Int){
        val id = id
        val helper = SqliteHelper(applicationContext)

        val seElimino = helper
            .eliminarUsuarioFormulario(
                id
            )

        Log.i("bdd", "Se elimino ${seElimino}")

    }
    fun mostrarDatos(usuario: Usuario){

        actualizarText_usuario_nombre.setText(usuario.nombre)
        actualizarText_usuario_apellido.setText(usuario.apellido)
        actualizarText_usuario_email.setText(usuario.email)
    }
}
