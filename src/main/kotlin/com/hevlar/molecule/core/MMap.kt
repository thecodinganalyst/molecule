package com.hevlar.molecule.core

import com.google.gson.Gson
import com.google.gson.JsonObject

object MMap: MType("MMap", MText, {
    try{
        Gson().fromJson(it, JsonObject::class.java) != null
    }catch (e: Throwable){
        false
    }
})