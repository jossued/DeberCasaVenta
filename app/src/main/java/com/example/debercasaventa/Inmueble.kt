package com.example.debercasaventa

import android.os.Parcel
import android.os.Parcelable

class Inmueble (var ciudad:String, var direccion:String, var m2: Int, var numDomitorios: Int, var publicacion: Publicacion):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readParcelable(Publicacion::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ciudad)
        parcel.writeString(direccion)
        parcel.writeInt(m2)
        parcel.writeInt(numDomitorios)
        parcel.writeParcelable(publicacion, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Inmueble> {
        override fun createFromParcel(parcel: Parcel): Inmueble {
            return Inmueble(parcel)
        }

        override fun newArray(size: Int): Array<Inmueble?> {
            return arrayOfNulls(size)
        }
    }

}