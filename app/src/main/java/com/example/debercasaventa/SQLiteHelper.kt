package com.example.debercasaventa

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import android.util.Log

class SqliteHelper(context: Context?) :
    SQLiteOpenHelper(
        context,
        "moviles", // Nombre de la base de datos
        null,
        1
    ) {

    override fun onCreate(baseDeDatos: SQLiteDatabase?) {

        val crearTablaUsuario = "CREATE TABLE " +
                "usuario " +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre VARCHAR(50)," +
                "apellido VARCHAR(50)," +
                "email VARCHAR(30)" +
                ")"
        val crearTablaPublicacion = "CREATE TABLE " +
                "publicacion " +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo VARCHAR(50)," +
                "costo DECIMAL(10,2)," +
                "fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL," +
                "id_usuario INTEGER,"+
                "CONSTRAINT fk_publicaciones " +
                "    FOREIGN KEY (id_usuario)" +
                "    REFERENCES usuario(id)"
                ")"
        Log.i("bdd", "Creando la tabla de usuario \n$crearTablaUsuario")
        baseDeDatos?.execSQL(crearTablaUsuario)
    }

    override fun onUpgrade(
        baseDeDatos: SQLiteDatabase?,
        antiguaVersion: Int,
        nuevaVersion: Int
    ) {

    }

    fun existeUsuarioFormulario(): RespuestaUsuarioGuardado {

        val statement = "select * from usuario where id=1;"

        val dbReadable = readableDatabase

        val resultado = dbReadable.rawQuery(statement, null)

        val respuestaUsuario = RespuestaUsuarioGuardado(null, null, null)

        if (resultado.moveToFirst()) {
            do {
                respuestaUsuario.nombre = resultado.getString(1)
                respuestaUsuario.apellido = resultado.getString(2)
                respuestaUsuario.email = resultado.getString(3)
            } while (resultado.moveToNext())
        }

        resultado.close()

        dbReadable.close()

        return respuestaUsuario
    }

    fun todosUsuarios(): ArrayList<Usuario>{
        val respuestaUsuario = ArrayList<Usuario>()
        val statement = "select * from usuario"

        val dbReadable = readableDatabase

        val resultado = dbReadable.rawQuery(statement, null)

        if (resultado.moveToFirst()) {
            do {
                val usuario = Usuario(
                    resultado.getInt(0),
                    resultado.getString(1),
                    resultado.getString(2),
                    resultado.getString(3)
                )
                respuestaUsuario.add(usuario)
            } while (resultado.moveToNext())
        }

        resultado.close()

        dbReadable.close()

        return respuestaUsuario
    }

    fun crearUsuarioFormulario(
        nombre: String,
        apellido: String, email: String
    ): Boolean {
        // Base de datos de escritura
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        // Valores de los campos
        cv.put("nombre", nombre)
        cv.put("apellido", apellido)
        cv.put("email", email)

        val resultado: Long = dbWriteable
            .insert(
                "usuario", // Nombre de la tabla
                null,
                cv
            )

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true

    }

    fun actualizarUsuarioFormulario(
        id:Int,
        nombre: String,
        apellido: String,
        email: String
    ): Boolean {
        // Base de datos de escritura
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        // Valores de los campos

        cv.put("nombre", nombre)
        cv.put("apellido", apellido)
        cv.put("email", email)

        val resultado = dbWriteable
            .update(
                "usuario", // Nombre de la tabla
                cv, // Valores a actualizarse
                "id=?", // Where
                arrayOf("${id}") // Parametros
            )

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true

    }

    fun eliminarUsuarioFormulario(id:Int): Boolean {
        val dbWriteable = writableDatabase

        val nombreTabla = "usuario"
        val clausulaWhere = "id = ?"
        val parametros = arrayOf("${id}")
        val respuesta = dbWriteable.delete(
            nombreTabla,
            clausulaWhere,
            parametros
        )
        return if (respuesta == -1) false else true
    }


    /*
    override fun onCreate(db: SQLiteDatabase?) {

        val createTableSQL = "CREATE TABLE ${BaseDeDatos.BDD_TABLA_USUARIO_NOMBRE} (${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_NOMBRE} VARCHAR(50))"

        db?.execSQL(createTableSQL)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertarUsuario(nombre: String) {
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        cv.put(BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_NOMBRE, nombre)

        val resultado = dbWriteable.insert(BaseDeDatos.BDD_TABLA_USUARIO_NOMBRE, null, cv)

        Log.i("database", "Si es -1 hubo error, sino exito: Respuesta: $resultado")

        dbWriteable.close()

    }

    fun leerDatos() {
        val dbReadable = readableDatabase

        val query = "SELECT * FROM ${BaseDeDatos.BDD_TABLA_USUARIO_NOMBRE}"

        val resultado = dbReadable.rawQuery(query, null)
        if (resultado.moveToFirst()) {
            do {
                val idActual = resultado.getString(0).toInt()
                val nombreActual = resultado.getString(1)
                Log.i("database", "El nombre es $nombreActual con id $idActual")
            } while (resultado.moveToNext())
        }
        resultado.close()
        dbReadable.close()
    }
*/
}






