package com.hevlar.molecule.core

import com.google.gson.Gson;
import com.google.gson.JsonArray

object Series: MType("MList", Text, {
    try{
        Gson().fromJson(it, JsonArray::class.java) != null
    }catch (e: Throwable){
        false
    }
}){
    override fun parse(value: String): List<Any>? {
        return try{
            Gson().fromJson(value, JsonArray::class.java).toList()
        }catch(e: Throwable){
            null
        }
    }
}
