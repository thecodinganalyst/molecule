package com.hevlar.molecule.core

import com.google.gson.Gson
import com.google.gson.JsonArray

object Series: MType("MList", Text, { value ->
    try{
        Gson().fromJson(value, JsonArray::class.java).toList()
    }catch(e: Throwable){
        null
    }
}){
    override fun parse(value: String): List<String>? {
        val series = this.parseFunction(value) as List<*>
        return series.map { it.toString() }
    }
}
