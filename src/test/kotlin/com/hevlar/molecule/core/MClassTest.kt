package com.hevlar.molecule.core

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MClassTest {

    @Test
    fun `MClass test ok`(){
        val json = """
            { "day": "Digit", "month": "Digit", "year": "Digit" }
        """.trimIndent()
        assertEquals(MClass, MClass.test(json))
    }

    @Test
    fun `test MClass invalid MType should fail`(){
        val json = """
            { "day": "Integer", "month": "Digit", "year": "Digit" }
        """.trimIndent()
        assertEquals(Data, MClass.test(json))
    }

    @Test
    fun `MClass parse ok`(){
        val json = """
            { "day": "Digit", "month": "Digit", "year": "Digit" }
        """.trimIndent()
        val expected = mapOf(
            "day" to Digit,
            "month" to Digit,
            "year" to Digit
        )
        assertEquals(expected, MClass.parse(json))
    }

    @Test
    fun `MClass parse null when type is invalid`(){
        val json = """
            { "day": "Integer", "month": "Digit", "year": "Digit" }
        """.trimIndent()
        assertNull(MClass.parse(json))
    }
}
