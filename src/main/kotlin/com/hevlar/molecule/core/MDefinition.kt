package com.hevlar.molecule.core

class MDefinition(val key: String, val type: MType){
}

class MData(val key: String, var value: String){
    var type: MType? = null
    constructor(key: String, value: String, type: MType?): this(key, value){
        this.type = type
    }
}
