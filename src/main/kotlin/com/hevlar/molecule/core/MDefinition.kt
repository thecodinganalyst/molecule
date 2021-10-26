package com.hevlar.molecule.core

/*
Definition is in the format { key: type } where key is the identifier of this definition
and type must be a subtype of MType. e.g. { name: Text } or { age: Number }
Definition can also contain multiple definitions, e.g { name: Text, age: Number }
 */
open class MDefinition(): MType("MDefinition", Data, { value ->
    try {
        val valueStr = if (value.trim().startsWith("{") && value.trim().endsWith("}")) value else "{ $value }"
        val map = Data.parse(valueStr) ?: throw Throwable("value for parseFunction is not a valid map")

        val defList = map.map {
            val typeInstance = getInstance(it.value as String) ?: throw Throwable("${it.value} is not a valid MType")
            MDefinition(it.key, typeInstance)
        }

        if (defList.size == 1){
            defList[0]
        } else {
            MDefinition(defList)
        }

    }catch (e: Throwable){
        null
    }
}){

    var key = "MDefinition"
    var type = parent
    var definitions = listOf<MDefinition>()

    constructor(key: String, type: MType) : this() {
        this.key = name
        this.type = type
    }

    constructor(definitions: List<MDefinition>) : this() {
        this.definitions = definitions
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
