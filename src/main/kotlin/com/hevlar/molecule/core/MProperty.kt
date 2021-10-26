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
    val typeInstance = getInstance(testMap["type"]!!) ?: throw Throwable("Invalid Property: type is not a valid MType")
    if (testMap.containsKey("required") && Flag.test(testMap["required"]!!) != Flag) {
        throw Throwable("Invalid Property: required field is not a valid flag")
    }
    if (testMap.containsKey("default")){
        if (typeInstance.test(testMap["default"]!!) == typeInstance) throw Throwable("Invalid Property: default value does not pass test of property type")
    }

    it
})
