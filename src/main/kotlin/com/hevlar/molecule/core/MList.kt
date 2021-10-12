package com.hevlar.molecule.core

import com.google.gson.Gson;
import com.google.gson.JsonArray

object MList: MType("MList", MText, {
    try{
        Gson().fromJson(it, JsonArray::class.java) != null
    }catch (e: Throwable){
        false
    }
})