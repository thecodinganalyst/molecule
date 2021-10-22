package com.hevlar.molecule.core

class MDefinition(name: String, type: MType): MType(name, MText, {
    val defaultPackage = "com.hevlar.molecule.core"
    try {
        val isMap = MMap.test(it) == MMap
        val map = MMap.parse(it)!!
        val mapContainsNameAndType = map.keys.containsAll(listOf("name", "type"))
        val givenType = map["type"].toString()
        val fqn = if (givenType.contains(".")) givenType else "${defaultPackage}.${givenType}"
        val testTypeExists = Class.forName(fqn).kotlin.objectInstance != null
        isMap && mapContainsNameAndType && testTypeExists
    }catch (e: Throwable){
        false
    }
})
