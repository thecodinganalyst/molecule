package com.hevlar.molecule.core

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/*
Class is a collection of definitions, it is a MMap where the values must be a MType
 */
object MClass: MType("MClass", Data, { value ->
    try {
        val type = object : TypeToken<Map<String, String>>(){}.type
        Gson().fromJson<Map<String, String>>(value, type)
            .mapValues { getInstance(it.value) ?: throw Throwable("Invalid type: ${it.value}") }
    }catch (e: Throwable){
        null
    }
}){
    override fun parse(value: String): Map<String, MType?>? {
        val tmp = this.parseFunction(value) as Map<*, *>?
        return tmp?.mapKeys { it.key as String }?.mapValues { it.value as MType }
    }
}
