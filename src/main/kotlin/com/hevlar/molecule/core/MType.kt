package com.hevlar.molecule.core

import java.math.BigDecimal
import java.math.BigInteger

interface Typeable{
    fun test(value: String): MType
    fun parse(value: String): Any?
}

open class MType(val name: String, val parent: Typeable?, val parseFunction: (String) -> Any?): Typeable{

    companion object: Typeable {
        fun getInstance(typeName: String): MType?{
            return parse(typeName)
        }

        override fun test(value: String): MType {
            return parse(value) ?: Text
        }

        override fun parse(value: String): MType? {
            return try {
                val defaultPackage = this::class.java.packageName
                val fqn = if (value.contains(".")) value else "${defaultPackage}.${value}"
                Class.forName(fqn).kotlin.objectInstance as MType
            }catch (e: Throwable){
                null
            }
        }
    }

    init {
        if (parent == null && name != "Text") throw Throwable("Only Text can have no parents")
    }

    override fun test(value: String): MType = if (parseFunction(value) != null){ this } else { parent!!.test(value) }

    override fun parse(value: String): Any? = parseFunction(value)

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
        return parseFunction(value) as BigDecimal?
    }
}
object Flag: MType("Flag", Text, { it.toBooleanStrictOrNull() }){
    override fun parse(value: String): Boolean? {
        return parseFunction(value) as Boolean?
    }
}
object Digit: MType("Digit", Number, { it.toBigIntegerOrNull() }){
    override fun parse(value: String): BigInteger? {
        return parseFunction(value) as BigInteger?
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
