package com.example.debercasaventa

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_usuarios.*

class UsuariosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuarios)

        val helper = SqliteHelper(applicationContext)

        val usuarios = helper.todosUsuarios()

//        BDD.leerBase(usuarios)
        val intentEditar = Intent(this, EditarUsuarioActivity::class.java)

        val layoutManager = LinearLayoutManager(this)
        val rv = rview_usuarios

        for (usuario in usuarios){Log.i("bdd", usuario.nombre)}
        val adaptador = PersonasAdaptador(usuarios, this, intentEditar)

        rview_usuarios.layoutManager = layoutManager
        rview_usuarios.itemAnimator = DefaultItemAnimator()
        rview_usuarios.adapter = adaptador

        adaptador.notifyDataSetChanged()
    }
    /*
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode){
            requestCodeActualizar -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        Log.i("intent-nombre-apellido", "LLEGOOOO ${data!!.getIntExtra("id", -1)}")
                        Log.i("intent-nombre-apellido", "LLEGOOOO ${data!!.getStringExtra("textoNombre")}")
                        Log.i("intent-nombre-apellido", "LLEGOOOO ${data!!.getStringExtra("textoApellido")}")
                        Log.i("intent-nombre-apellido", "LLEGOOOO ${data!!.getStringExtra("textoEmail")}")

                        actualizarUsuario(
                            data!!.getIntExtra("id", -1), data!!.getStringExtra("textoNombre"),
                            data!!.getStringExtra("textoApellido"), data!!.getStringExtra("textoEmail")
                        )

                    }

                    RESULT_CANCELED -> {
                        Log.i("error", "Error")
                    }
                }
            }

        }
    }

    fun actualizarUsuario(id: Int, textoNombre: String, textoApellido: String, textoEmail: String) {


        val helper = SqliteHelper(applicationContext)

//        val noExisteRegistroDeUsuario = helper.existeUsuarioFormulario().nombre == null


        val seActualizo = helper
            .actualizarUsuarioFormulario(
                id,
                textoNombre,
                textoApellido,
                textoEmail
            )

        Log.i("bdd", "Se actualizo ${seActualizo}")
    }

    companion object {
        val requestCodeActualizar = 101
    }
    */

}

class PersonasAdaptador(val listaPersonas: ArrayList<Usuario>, private val contexto: UsuariosActivity, intent: Intent) :
    RecyclerView.Adapter<PersonasAdaptador.MyViewHolder>() {


    val intentEditar = intent

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView
        var apellidoTextView: TextView


        init {
            nombreTextView = view.findViewById(R.id.textView_nombre) as TextView
            apellidoTextView = view.findViewById(R.id.textView_apellido) as TextView

            val boton = view.findViewById(R.id.button_ver_usuario) as Button

            boton
                .setOnClickListener {
                    val usuario = listaPersonas[position]
                    Log.i("paso", "${usuario.nombre}, ${usuario.apellido}, ${usuario.email}, ${usuario.id}")
                    intentEditar.putExtra("id_usuario",usuario.id)

                    val usuario_pasar = Usuario(usuario.id, usuario.nombre, usuario.apellido, usuario.email)
                    intentEditar.putExtra("usuario_pasar",usuario_pasar)
                    contexto.startActivity(intentEditar)
//                    contexto.startActivityForResult(intentEditar, requestCodeActualizar)
                    contexto.finish()
//                    contexto.recreate()

                }


        }




    }


    // Definimos el layout
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): MyViewHolder {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.lista_usuarios_rvlayout,
                parent,
                false
            )

        return MyViewHolder(itemView)
    }

    // Llenamos los datos del layout
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val persona = listaPersonas[position]

        holder.nombreTextView.setText(persona.nombre)
        holder.apellidoTextView.setText(persona.apellido)

        /*
        holder.itemView.setOnClickListener {
            @Override
            public void onClick(View view) {
                startActivity
            }
        }
        */
    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }
    companion object {
        val requestCodeActualizar = 101
    }

}

