package com.hevlar.molecule.core

import kotlin.reflect.typeOf

/*
Definition is in the format { name: type } where name is the identifier of this definition
and type must be a subtype of MType. e.g. { name: MText } or { age: MNumber }
 */
class MDefinition(name: String, val type: MType): MType(name, MMap, {
    val defaultPackage = "com.hevlar.molecule.core"
    try {
        val itStr = if (it.trim().startsWith("{") && it.trim().endsWith("}")) it else "{ $it }"
        val isMap = MMap.test(itStr) == MMap
        val map = MMap.parse(itStr)!!
        val givenType = map.values.first().toString();
        val fqn = if (givenType.contains(".")) givenType else "${defaultPackage}.${givenType}"
        val typeInstance = Class.forName(fqn).kotlin.objectInstance
        isMap && map.count() == 1 && typeInstance != null && typeInstance is MType
    }catch (e: Throwable){
        false
    }
}){

    override fun parse(value: String): MDefinition? {
        val defaultPackage = "com.hevlar.molecule.core"
        return try {
            val valueStr = if (value.trim().startsWith("{") && value.trim().endsWith("}")) value else "{ $value }"
            val isMap = MMap.test(valueStr) == MMap
            val map = MMap.parse(valueStr)!!
            val givenType = map.values.first().toString();
            val fqn = if (givenType.contains(".")) givenType else "${defaultPackage}.${givenType}"
            val typeInstance = Class.forName(fqn).kotlin.objectInstance
            if(map.count() == 1 && typeInstance != null && typeInstance is MType){
                MDefinition(map.keys.first(), typeInstance)
            }else{
                null
            }
        }catch (e: Throwable){
            null
        }
    }

    override fun equals(other: Any?): Boolean {
        if(other !is MDefinition) return false
        return this.name == other.name && this.type == other.type
    }
}
