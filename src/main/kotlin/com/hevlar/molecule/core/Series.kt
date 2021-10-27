package com.hevlar.molecule.core

import com.google.gson.Gson
import com.google.gson.JsonArray

object Series: MType("Series", Text, { value ->
    try{
        Gson().fromJson(value, JsonArray::class.java)
            .map {
                if (it.isJsonPrimitive){
                    if (it.asJsonPrimitive.isBoolean){
                        it.asJsonPrimitive.asBoolean
                    } else if (it.asJsonPrimitive.isNumber){
                        try {
                            it.asJsonPrimitive.asBigInteger
                        }catch (e2: Throwable){
                            it.asJsonPrimitive.asBigDecimal
                        }
                    } else {
                        it.asJsonPrimitive.asString
                    }
                } else if (it.isJsonObject) {
                    Data.parse(it.asJsonObject.toString())
                } else if (it.isJsonArray) {
                    Series.parse(it.asJsonArray.toString())
                }else{
                    null
                }
            }
    }catch(e: Throwable){
        null
    }
}){
    override fun parse(value: String): List<Any?>? {
        return this.parseFunction(value) as List<Any?>?
    }
}
