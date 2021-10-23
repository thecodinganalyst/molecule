package com.hevlar.molecule.core

import kotlin.reflect.typeOf

/*
Definition is in the format { name: type } where name is the identifier of this definition
and type must be a subtype of MType. e.g. { name: MText } or { age: MNumber }
 */
class MDefinition(name: String, val type: MType): MType(name, MMap, {
    val defaultPackage = "com.hevlar.molecule.core"
    try {
        val isMap = MMap.test(it) == MMap
        val map = MMap.parse(it)!!
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
            val isMap = MMap.test(value) == MMap
            val map = MMap.parse(value)!!
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
