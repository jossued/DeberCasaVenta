package com.example.debercasaventa

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

        val layoutManager = LinearLayoutManager(this)
        val rv = rview_usuarios

        for (usuario in usuarios){Log.i("bdd", usuario.nombre)}
        val adaptador = PersonasAdaptador(usuarios)

        rview_usuarios.layoutManager = layoutManager
        rview_usuarios.itemAnimator = DefaultItemAnimator()
        rview_usuarios.adapter = adaptador

        adaptador.notifyDataSetChanged()
    }

}

class PersonasAdaptador(private val listaPersonas: List<Usuario>) :
    RecyclerView.Adapter<PersonasAdaptador.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView
        var apellidoTextView: TextView

        init {
            nombreTextView = view.findViewById(R.id.textView_nombre) as TextView
            apellidoTextView = view.findViewById(R.id.textView_apellido) as TextView

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
    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }

}

class BDD {
    companion object {
        var usuarios = ArrayList<Usuario>()

        fun crearMas() {
            usuarios.add(Usuario("Juan", "353331223", "s"))
            usuarios.add(Usuario("Adrian", "12312312", "sa"))
            usuarios.add(Usuario("Vicente", "98734833", "saf"))
        }

        fun leerbase(usuario: ArrayList<Usuario>){
            var usuarios = usuario
        }
    }
}
