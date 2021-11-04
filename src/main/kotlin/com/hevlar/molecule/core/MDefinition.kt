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
    var type: Typeable = Data
    var definitions = listOf<MDefinition>()

    constructor(key: String, type: Typeable) : this() {
        this.key = key
        this.type = type
    }

    constructor(definitions: List<MDefinition>) : this() {
        this.definitions = definitions
    }

    companion object {
        fun test(value: String): MType {
            return if (MDefinition().parse(value) != null) MDefinition() else Data.test(value)
        }

        fun parse(value: String): MDefinition? {
            return MDefinition().parse(value)
        }
    }

    override fun test(value: String): MType {
        try {
            val valueMap = Data.parse(value)
            if (definitions.isEmpty()){
                if (test(key, valueMap!![key]) == this) return this
            } else {
                val allDefPass = definitions.all { it.test(it.key, valueMap!![it.key]) == it }
                if (allDefPass) return this
            }
        }catch (e: Throwable){
            return this.parent!!.test(value)
        }
        return this.parent!!.test(value)
    }

    fun test(key: String, value: Any?): MType{
        return try {
          if (this.key == key && type!!.test(value.toString()) == type) {
              this
          } else {
              this.parent!!.test(value.toString())
          }
        } catch (e: Throwable) {
            this.parent!!.test(value.toString())
        }
    }

    override fun parse(value: String): MDefinition? {
        return this.parseFunction(value) as MDefinition?
    }
}
