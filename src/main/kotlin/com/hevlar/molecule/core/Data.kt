package com.hevlar.molecule.core

import com.google.gson.Gson
import com.google.gson.JsonObject

object Data: MType("MMap", Text, {
    try{
        Gson().fromJson(it, JsonObject::class.java) != null
    }catch (e: Throwable){
        false
    }
}){
    override fun parse(value: String): Map<String, Any>? {
        return try{
            return Gson()
                .fromJson(value, JsonObject::class.java)
                .entrySet()
                .associate { it.key to it.value }
                .mapValues {
                    if (it.value.isJsonPrimitive) {
                        val primitive = it.value.asJsonPrimitive
                        if (primitive.isBoolean) {
                            it.value.asJsonPrimitive.asBoolean
                        } else if (primitive.isNumber) {
                            it.value.asJsonPrimitive.asBigDecimal
                        } else {
                            it.value.asString
                        }
                    }else if (it.value.isJsonObject){
                        parse(it.value.toString()) ?: {}
                    }else{
                        it.value
                    }
                }
        }catch (e: Throwable){
            null
        }
    }
}
