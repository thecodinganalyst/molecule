package com.hevlar.molecule.core

import java.math.BigDecimal
import java.math.BigInteger

interface Testable{
    fun test(value: String): MType
}

open class MType(val name: String, val parent: MType?, private val parseFunction: (String) -> Any?): Testable{

    companion object {
        fun getInstance(typeName: String): MType{
            val defaultPackage = this::class.java.packageName
            val fqn = if (typeName.contains(".")) typeName else "${defaultPackage}.${typeName}"
            return Class.forName(fqn).kotlin.objectInstance as MType
        }
    }

    init {
        if (parent == null && name != "Text") throw Throwable("Only Text can have no parents")
    }

    override fun test(value: String): MType = if (parseFunction(value) != null){ this } else { parent!!.test(value) }

    open fun parse(value: String): Any? = parseFunction(value)

    override fun equals(other: Any?): Boolean {
        if (other !is MType) return false
        return this.name == other.name && this.parent == other.parent
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (parent?.hashCode() ?: 0)
        result = 31 * result + parseFunction.hashCode()
        return result
    }
}

object Text: MType("Text", null, { it })

object Number: MType("Number", Text, { it.toBigDecimalOrNull() }){
    override fun parse(value: String): BigDecimal? {
        return value.toBigDecimalOrNull()
    }
}
object Flag: MType("Flag", Text, { it.toBooleanStrictOrNull() }){
    override fun parse(value: String): Boolean? {
        return value.toBooleanStrictOrNull()
    }
}
object Digit: MType("Digit", Number, { it.toBigIntegerOrNull() }){
    override fun parse(value: String): BigInteger? {
        return value.toBigIntegerOrNull()
    }
}

object MTypeLibrary {
    private val types: MutableMap<String, MType> = mutableMapOf(
        Text.name to Text,
        Number.name to Number,
        Flag.name to Flag,
        Digit.name to Digit
    )

    /* adds a new type into library if the key is not associated, returning true, and false otherwise */
    fun register(newType: MType) = types.putIfAbsent(newType.name, newType) == null

    fun get(typeName: String) = types[typeName]
}
