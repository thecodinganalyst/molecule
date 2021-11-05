package com.hevlar.molecule.core

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.math.BigInteger

internal class MClassTest {

    @Test
    fun `MProperty test ok`(){
        val json = """
            {
                "type"      : "Digit",
                "required"  : true,
                "default"   : 1
            }
        """.trimIndent()
        assertEquals(MProperty(), MProperty.test(json))
    }

    @Test
    fun `MProperty parse ok`(){
        val json = """
            {
                "type"      : "Digit",
                "required"  : true,
                "default"   : 1
            }
        """.trimIndent()
        val expected = mapOf(
            "type" to "Digit",
            "required" to true,
            "default" to BigInteger("1")
        )
        val actual = MProperty.parse(json);
        assertTrue(expected == actual)
    }

    @Test
    fun `MProperty test ok with array`(){
        val json = """
            ["Digit", true, 1]
        """.trimIndent()
        assertEquals(MProperty(), MProperty.test(json))
    }

    @Test
    fun `MProperty parse ok with array`(){
        val json = """
            ["Digit", true, 1]
        """.trimIndent()
        val expected = mapOf(
            "type" to "Digit",
            "required" to true,
            "default" to BigInteger("1")
        )
        val actual = MProperty.parse(json);
        assertTrue(expected == actual)
    }

//    @Test
//    fun `MClass test ok with properties`(){
//        val json = """
//            {
//                "day": ["Digit", true, 1],
//                "month": ["Digit", true, 1],
//                "year": ["Digit", true, 2021]
//            }
//        """.trimIndent()
//        assertEquals(MClass, MClass.test(json))
//    }

}
