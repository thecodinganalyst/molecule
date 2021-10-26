package com.hevlar.molecule.core

/*
Method has a map of input definitions and an output definition
e.g. { inputs: { name: MText, age: MInteger }, output: MBoolean }
 */
object MMethod: MType("MMethod", Data, {
    try {
        val isMap = Data.test(it) == Data
        val map = Data.parse(it)
        val inputs = map!!["inputs"] as Map<*,*>
        val inputsAreDefinitions = inputs.all { input -> MDefinition.test("${input.key}: ${input.value}") == MDefinition() }
        val outputIsMType = getInstance(map["output"].toString()) != null

        if (isMap && map.containsKey("inputs") && map.containsKey("output") && inputsAreDefinitions && outputIsMType){
            it
        }else{
            null
        }
    }catch (e: Throwable){
        null
    }
})
