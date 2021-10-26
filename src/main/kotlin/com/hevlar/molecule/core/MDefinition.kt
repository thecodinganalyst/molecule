package com.hevlar.molecule.core

/*
Definition is in the format { name: type } where name is the identifier of this definition
and type must be a subtype of MType. e.g. { name: Text } or { age: Number }
 */
open class MDefinition(): MType("MDefinition", Data, { value ->
    try {
        val valueStr = if (value.trim().startsWith("{") && value.trim().endsWith("}")) value else "{ $value }"
        val isMap = Data.test(valueStr) == Data
        val map = Data.parse(valueStr)!!
        val givenType = map.values.first().toString()
        val typeInstance = getInstance(givenType)
        if(isMap && map.count() == 1 && typeInstance != null){
            MDefinition(map.keys.first(), typeInstance)
        }else{
            null
        }
    }catch (e: Throwable){
        null
    }
}){

    var type = parent
    constructor(name: String, type: MType) : this() {
        this.type = type
    }

    companion object {
        fun test(value: String): MType {
            return MDefinition().test(value)
        }

        fun parse(value: String): MDefinition? {
            return MDefinition().parse(value)
        }
    }

    override fun parse(value: String): MDefinition? {
        return this.parseFunction(value) as MDefinition?
    }
}
