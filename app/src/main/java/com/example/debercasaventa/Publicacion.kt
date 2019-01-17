package com.example.debercasaventa

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Publicacion (var tipo: String, var titulo: String, var fecha:Date, var precio:Double, var usuario: Usuario):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readSerializable() as Date,
        parcel.readDouble(),
        parcel.readParcelable(Usuario::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(tipo)
        parcel.writeString(titulo)
        parcel.writeDouble(precio)
        parcel.writeParcelable(usuario, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Publicacion> {
        override fun createFromParcel(parcel: Parcel): Publicacion {
            return Publicacion(parcel)
        }

        override fun newArray(size: Int): Array<Publicacion?> {
            return arrayOfNulls(size)
        }
    }

}