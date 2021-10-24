package com.hevlar.molecule.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MDefinitionTest{

    @Test
    fun `test MDefinition MText ok`(){
        val json = """
            { "name": "MText" }
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition MBoolean ok`(){
        val json = """
            { "Registered": "MBoolean" }
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition MNumber ok`(){
        val json = """
            { "Years of Experience": "MNumber" }
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition MInteger ok`(){
        val json = """
            { "Age": "MInteger" }
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition multiple values should fail`(){
        val json = """
            { "Name": "MText", "Age": "MInteger" }
        """.trimIndent()
        assertNotEquals(MDefinition, MDefinition.test(json))
        assertEquals(MMap, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition invalid MType should fail`(){
        val json = """
            { "Name: "Text" }
        """.trimIndent()
        assertNotEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `parse MDefinition MText ok`(){
        val json = """
            { "Name": "MText" }
        """.trimIndent()
        assertEquals(Pair("Name", MText), MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MBoolean ok`(){
        val json = """
            { "Registered": "MBoolean" }
        """.trimIndent()
        assertEquals(Pair("Registered", MBoolean), MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MNumber ok`(){
        val json = """
            { "Years of Experience": "MNumber" }
        """.trimIndent()
        assertEquals(Pair("Years of Experience", MNumber), MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MInteger ok`(){
        val json = """
            { "Age": "MInteger" }
        """.trimIndent()
        assertEquals(Pair("Age", MInteger), MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition multiple values should return null`(){
        val json = """
            { "Name: "MText", "Age", "MInteger" }
        """.trimIndent()
        assertNull(MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition invalid MType should fail`(){
        val json = """
            { "Name: "Text" }
        """.trimIndent()
        assertNull(MDefinition.parse(json))
    }

    @Test
    fun `test MDefinition MText ok without braces`(){
        val json = """
            "name": "MText"
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition MBoolean ok without braces`(){
        val json = """
             "Registered": "MBoolean" 
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition MNumber ok without braces`(){
        val json = """
            "Years of Experience": "MNumber"
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition MInteger ok without braces`(){
        val json = """
             "Age": "MInteger" 
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition multiple values without braces should fail too`(){
        val json = """
             "Name: "MText", "Age", "MInteger" 
        """.trimIndent()
        assertNotEquals(MDefinition, MDefinition.test(json))
        assertEquals(MText, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition invalid MType should fail without braces`(){
        val json = """
             "Name: "Text" 
        """.trimIndent()
        assertNotEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `parse MDefinition MText ok without braces`(){
        val json = """
             "Name": "MText" 
        """.trimIndent()
        val expected = Pair("Name", MText)
        assertEquals(expected, MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MBoolean ok without braces`(){
        val json = """
            "Registered": "MBoolean"
        """.trimIndent()
        val expected = Pair("Registered", MBoolean)
        assertEquals(expected, MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MNumber ok without braces`(){
        val json = """
            "Years of Experience": "MNumber"
        """.trimIndent()
        val expected = Pair("Years of Experience", MNumber)
        assertEquals(expected, MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MInteger ok without braces`(){
        val json = """
             "Age": "MInteger" 
        """.trimIndent()
        val expected = Pair("Age", MInteger)
        assertEquals(expected, MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition multiple values without braces should return null`(){
        val json = """
            "Name: "MText", "Age", "MInteger"
        """.trimIndent()
        assertNull(MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition invalid MType should fail without braces too`(){
        val json = """
             "Name: "Text"
        """.trimIndent()
        assertNull(MDefinition.parse(json))
    }
}
