package com.hevlar.molecule.core

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class MDefinition(val key: String, value: Map<String, MType>): MType(key, MMap, {
    val testMap = try {
        val type = object : TypeToken<Map<String, String>>(){}.type
        Gson().fromJson<Map<String, String>>(it, type)
    }catch (e: Throwable){
        throw e
    }

    testMap.keys.containsAll(value.keys) && testMap.all { test ->
        val propType = value[test.key]
        propType?.test(test.value) == propType
    }
})
