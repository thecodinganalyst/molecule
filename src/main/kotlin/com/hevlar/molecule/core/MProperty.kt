package com.hevlar.molecule.core

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/*
MProperty defines the properties for a class, with the following attributes
1. type
2. default - optional
3. required - default true
 */
open class MProperty() : MType("MProperty", Data, {
    val testMap = try {
        val type = object : TypeToken<Map<String, String>>(){}.type
        Gson().fromJson<Map<String, String>>(it, type)
    }catch (e: Throwable){
        throw e
    }

    try {
        if (!testMap.containsKey("type")) throw Throwable("Invalid Property: type is not defined in property declaration")
        val typeInstance = getInstance(testMap["type"]!!) ?: throw Throwable("Invalid Property: type is not a valid MType")
        if (testMap.containsKey("required") && Flag.test(testMap["required"]!!) != Flag) {
            throw Throwable("Invalid Property: required field is not a valid flag")
        }
        if (testMap.containsKey("default")){
            val defaultValue = testMap["default"]
            val testDefaultValue = typeInstance.test(defaultValue!!)
            if (testDefaultValue != typeInstance) throw Throwable("Invalid Property: default value does not pass test of property type")
        }

        MProperty(
            typeInstance,
            testMap["default"],
            if (testMap["required"] != null) Flag.parse(testMap["required"]!!) else null
        )
    }catch (e: Throwable){
        null
    }

}){
    var type: MType? = null
    var default: Any? = null
    var required = true
    constructor(type: MType, default: Any?, required: Boolean?) : this() {
        this.type = type
        this.default = default
        this.required = required?: true
    }

    companion object {
        fun test(value: String): MType {
            return if (MProperty().parse(value) != null) MProperty() else Data.test(value)
        }

        fun parse(value: String): MProperty? {

            val series = Series.parse(value)
            if (series != null) {
                val type = parse("type: ${series[0]}")
                val default = if (series.size >= 1) "default: ${series[1]}" else ""
                val required = if(series.size >= 2) "required: ${series[2]}" else ""
                return MProperty().parse("$type $default $required")
            }

            return MProperty().parse(value)
        }
    }

    override fun parse(value: String): MProperty? {
        return this.parseFunction(value) as MProperty?
    }
}
