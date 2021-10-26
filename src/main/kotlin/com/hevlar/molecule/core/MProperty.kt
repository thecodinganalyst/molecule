package com.hevlar.molecule.core

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/*
MProperty defines the properties for a class, with the following attributes
1. type
2. required - default true
3. default - optional
 */
object MProperty : MType("MProperty", Data, {
    val testMap = try {
        val type = object : TypeToken<Map<String, String>>(){}.type
        Gson().fromJson<Map<String, String>>(it, type)
    }catch (e: Throwable){
        throw e
    }

    if (!testMap.containsKey("type")) throw Throwable("Invalid Property: type is not defined in property declaration")
    if (MDefinition.test(testMap["type"]!!) != MDefinition) throw Throwable("Invalid Property: type is not a valid MDefinition")
    if (testMap.containsKey("required") && Flag.test(testMap["required"]!!) != Flag) {
        throw Throwable("Invalid Property: required field is not a valid boolean")
    }
    if (testMap.containsKey("default")){
        try {
            val typeInstance = getInstance(testMap["type"]!!)
            if (typeInstance.test(testMap["default"]!!) == typeInstance) throw Throwable("Invalid Property: default value does not pass test of property type")
        }catch (e: Throwable){
            throw Throwable("Invalid Property: Error with the default")
        }
    }

    it
})
