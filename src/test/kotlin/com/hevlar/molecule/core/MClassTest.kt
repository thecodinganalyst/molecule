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
}
