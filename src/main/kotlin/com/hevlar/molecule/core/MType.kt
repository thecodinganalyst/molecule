package com.hevlar.molecule.core

import java.math.BigDecimal
import java.math.BigInteger

interface Testable{
    fun test(value: String): MType
}

open class MType(val name: String, val parent: MType?, val testFunction: (String) -> Boolean): Testable{

    init {
        if (parent == null && name != "MText") throw Throwable("Only MText can have no parents")
    }

    override fun test(value: String): MType = if (testFunction(value)){ this } else { parent!!.test(value) }

    open fun parse(value: String): Any? = value
}

object MText: MType("MText", null, { true })

object MNumber: MType("MNumber", MText, { it.toBigDecimalOrNull() != null }){
    override fun parse(value: String): BigDecimal? {
        return value.toBigDecimalOrNull()
    }
}
object MBoolean: MType("MBoolean", MText, { it.toBooleanStrictOrNull() == true || it.toBooleanStrictOrNull() == false }){
    override fun parse(value: String): Boolean? {
        return value.toBooleanStrictOrNull()
    }
}
object MInteger: MType("MInteger", MNumber, { it.toBigIntegerOrNull() != null }){
    override fun parse(value: String): BigInteger? {
        return value.toBigIntegerOrNull();
    }
}

object MTypeLibrary {
    private val types: MutableMap<String, MType> = mutableMapOf(
        MText.name to MText,
        MNumber.name to MNumber,
        MBoolean.name to MBoolean,
        MInteger.name to MInteger
    )

    /* adds a new type into library if the key is not associated, returning true, and false otherwise */
    fun register(newType: MType) = types.putIfAbsent(newType.name, newType) == null

    fun get(typeName: String) = types[typeName]
}
