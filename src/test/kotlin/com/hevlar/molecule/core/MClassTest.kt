package com.hevlar.molecule.core

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MClassTest {

    @Test
    fun `MClass test ok`(){
        val json = """
            { "day": "MInteger", "month": "MInteger", "year": "MInteger" }
        """.trimIndent()
        assertEquals(MClass, MClass.test(json))
    }

    @Test
    fun `test MClass invalid MType should fail`(){
        val json = """
            { "day": "Integer", "month": "MInteger", "year": "MInteger" }
        """.trimIndent()
        assertEquals(MMap, MClass.test(json))
    }

    @Test
    fun `MClass parse ok`(){
        val json = """
            { "day": "MInteger", "month": "MInteger", "year": "MInteger" }
        """.trimIndent()
        val expected = mapOf(
            "day" to MInteger,
            "month" to MInteger,
            "year" to MInteger
        )
        assertEquals(expected, MClass.parse(json))
    }

    @Test
    fun `MClass parse null when type is invalid`(){
        val json = """
            { "day": "Integer", "month": "MInteger", "year": "MInteger" }
        """.trimIndent()
        assertNull(MClass.parse(json))
    }
}
