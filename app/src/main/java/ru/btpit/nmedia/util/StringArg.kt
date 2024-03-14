package ru.btpit.nmedia.util

import android.os.Bundle
import kotlin.reflect.KProperty
import kotlin.properties.ReadWriteProperty

object StringArg: ReadWriteProperty<Bundle, String?> {

    override fun setValue(thisRef: Bundle, property: KProperty<*>, value: String?) {
        thisRef.putString(property.name, value)
    }


    override fun getValue(thisRef: Bundle, property: KProperty<*>): String? =
        thisRef.getString(property.name)
}