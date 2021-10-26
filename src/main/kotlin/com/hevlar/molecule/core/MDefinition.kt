package com.hevlar.molecule.core

/*
Definition is in the format { name: type } where name is the identifier of this definition
and type must be a subtype of MType. e.g. { name: MText } or { age: MNumber }
 */
object MDefinition: MType("MDefinition", Data, { value ->
    try {
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
}){

    override fun parse(value: String): Pair<String, MType>? {
        val tmp = this.parseFunction(value) as Pair<*, *>?
        return if (tmp != null)
            Pair(tmp.first as String, tmp.second as MType)
        else
            null
    }
}
