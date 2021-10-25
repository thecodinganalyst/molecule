package com.hevlar.molecule.core

/*
Definition is in the format { name: type } where name is the identifier of this definition
and type must be a subtype of MType. e.g. { name: MText } or { age: MNumber }
 */
object MDefinition: MType("MDefinition", Data, {
    try {
        val itStr = if (it.trim().startsWith("{") && it.trim().endsWith("}")) it else "{ $it }"
        val isMap = Data.test(itStr) == Data
        val map = Data.parse(itStr)!!
        val givenType = map.values.first().toString()
        getInstance(givenType)
        isMap && map.count() == 1
    }catch (e: Throwable){
        false
    }
}){

    override fun parse(value: String): Pair<String, MType>? {
        return try {
            val valueStr = if (value.trim().startsWith("{") && value.trim().endsWith("}")) value else "{ $value }"
            val isMap = Data.test(valueStr) == Data
            val map = Data.parse(valueStr)!!
            val givenType = map.values.first().toString()
            val typeInstance = getInstance(givenType)
            if(isMap && map.count() == 1){
                Pair(map.keys.first(), typeInstance)
            }else{
                null
            }
        }catch (e: Throwable){
            null
        }
    }
}
