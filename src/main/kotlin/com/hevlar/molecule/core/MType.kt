package com.hevlar.molecule.core

interface Testable{
    fun test(value: String): MType
}

open class MType(val name: String, val parent: MType?, val testFunction: (String) -> Boolean): Testable{

    init {
        if (parent == null && name != "MText") throw Throwable("Only MText can have no parents")
    }

    override fun test(value: String): MType = if (testFunction(value)){ this } else { parent!!.test(value) }
}

object MText: MType("MText", null, { true })
object MNumber: MType("MNumber", MText, { it.toBigDecimalOrNull() != null })
object MBoolean: MType("MBoolean", MText, { it.toBooleanStrictOrNull() == true || it.toBooleanStrictOrNull() == false })
object MInteger: MType("MInteger", MNumber, { it.toBigIntegerOrNull() != null })

object MTypeLibrary {
    private val types: MutableMap<String, MType> = mutableMapOf(
        MText.name to MText,
        MNumber.name to MNumber,
        MBoolean.name to MBoolean,
        MInteger.name to MInteger
    )
}
