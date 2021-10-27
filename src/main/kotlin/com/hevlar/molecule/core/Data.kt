package com.hevlar.molecule.core

import com.google.gson.Gson
import com.google.gson.JsonObject

object Data: MType("Data", Text, { value ->
    try{
        Gson()
            .fromJson(value, JsonObject::class.java)
            .entrySet()
            .associate { it.key to it.value }
            .mapValues {
                if (it.value.isJsonPrimitive) {
                    val primitive = it.value.asJsonPrimitive
                    if (primitive.isBoolean) {
                        it.value.asJsonPrimitive.asBoolean
                    } else if (primitive.isNumber) {
                        try {
                            it.value.asJsonPrimitive.asBigInteger
                        }catch (e2: Throwable){
                            it.value.asJsonPrimitive.asBigDecimal
                        }
                    } else {
                        it.value.asJsonPrimitive.asString
                    }
                }else if (it.value.isJsonObject) {
                    Data.parse(it.value.toString())
                }else if (it.value.isJsonArray){
                    Series.parse(it.value.asJsonArray.toString())
                }else{
                    it.value
                }
            }
    }catch (e: Throwable){
        null
    }
}){
    override fun parse(value: String): Map<String, Any?>? {
        val tmp =  this.parseFunction(value) as Map<*, *>?
        return tmp?.mapKeys { it.key as String }
    }
}
