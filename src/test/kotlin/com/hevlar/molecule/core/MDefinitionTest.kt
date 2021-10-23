package com.hevlar.molecule.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.expect

internal class MDefinitionTest{

    @Test
    fun `test MDefinition MText ok`(){
        val json = """
            { "name": "MText" }
        """.trimIndent()
        val expected = MDefinition("Name", MText)
        assertEquals(expected, expected.test(json))
    }

    @Test
    fun `test MDefinition MBoolean ok`(){
        val json = """
            { "Registered": "MBoolean" }
        """.trimIndent()
        val expected = MDefinition("Registered", MBoolean)
        assertEquals(expected, expected.test(json))
    }

    @Test
    fun `test MDefinition MNumber ok`(){
        val json = """
            { "Years of Experience": "MNumber" }
        """.trimIndent()
        val expected = MDefinition("Years of Experience", MNumber)
        assertEquals(expected, expected.test(json))
    }

    @Test
    fun `test MDefinition MInteger ok`(){
        val json = """
            { "Age": "MInteger" }
        """.trimIndent()
        val expected = MDefinition("Age", MInteger)
        assertEquals(expected, expected.test(json))
    }

    @Test
    fun `test MDefinition mismatch should fail`(){
        val json = """
            { "Name: "MText" }
        """.trimIndent()
        val expected = MDefinition("Age", MInteger)
        assertNotEquals(expected, expected.test(json))
    }

    @Test
    fun `test MDefinition multiple values should fail`(){
        val json = """
            { "Name: "MText", "Age", "MInteger" }
        """.trimIndent()
        val expected = MDefinition("Age", MInteger)
        assertNotEquals(expected, expected.test(json))
    }

    @Test
    fun `test MDefinition invalid MType should fail`(){
        val json = """
            { "Name: "Text" }
        """.trimIndent()
        val expected = MDefinition("Name", MText)
        assertNotEquals(expected, expected.test(json))
    }

    @Test
    fun `parse MDefinition MText ok`(){
        val json = """
            { "Name": "MText" }
        """.trimIndent()
        val expected = MDefinition("Name", MText)
        assertEquals(expected, expected.parse(json))
    }

    @Test
    fun `parse MDefinition MBoolean ok`(){
        val json = """
            { "Registered": "MBoolean" }
        """.trimIndent()
        val expected = MDefinition("Registered", MBoolean)
        assertEquals(expected, expected.parse(json))
    }

    @Test
    fun `parse MDefinition MNumber ok`(){
        val json = """
            { "Years of Experience": "MNumber" }
        """.trimIndent()
        val expected = MDefinition("Years of Experience", MNumber)
        assertEquals(expected, expected.parse(json))
    }

    @Test
    fun `parse MDefinition MInteger ok`(){
        val json = """
            { "Age": "MInteger" }
        """.trimIndent()
        val expected = MDefinition("Age", MInteger)
        assertEquals(expected, expected.parse(json))
    }

    @Test
    fun `parse MDefinition mismatch should return null`(){
        val json = """
            { "Name: "MText" }
        """.trimIndent()
        val expected = MDefinition("Age", MInteger)
        assertNull(expected.parse(json))
    }

    @Test
    fun `parse MDefinition multiple values should return null`(){
        val json = """
            { "Name: "MText", "Age", "MInteger" }
        """.trimIndent()
        val expected = MDefinition("Age", MInteger)
        assertNull(expected.parse(json))
    }

    @Test
    fun `parse MDefinition invalid MType should fail`(){
        val json = """
            { "Name: "Text" }
        """.trimIndent()
        val expected = MDefinition("Name", MText)
        assertNull(expected.parse(json))
    }
}
