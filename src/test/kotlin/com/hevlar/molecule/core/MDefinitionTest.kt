package com.hevlar.molecule.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MDefinitionTest{

    @Test
    fun `test MDefinition MText ok`(){
        val json = """
            { "name": "Text" }
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition MBoolean ok`(){
        val json = """
            { "Registered": "Flag" }
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition MNumber ok`(){
        val json = """
            { "Years of Experience": "Number" }
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition MInteger ok`(){
        val json = """
            { "Age": "Digit" }
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition multiple values should fail`(){
        val json = """
            { "Name": "Text", "Age": "Digit" }
        """.trimIndent()
        assertNotEquals(MDefinition, MDefinition.test(json))
        assertEquals(Data, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition invalid MType should fail`(){
        val json = """
            { "Name: "MText" }
        """.trimIndent()
        assertNotEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `parse MDefinition MText ok`(){
        val json = """
            { "Name": "Text" }
        """.trimIndent()
        assertEquals(Pair("Name", Text), MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MBoolean ok`(){
        val json = """
            { "Registered": "Flag" }
        """.trimIndent()
        assertEquals(Pair("Registered", Flag), MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MNumber ok`(){
        val json = """
            { "Years of Experience": "Number" }
        """.trimIndent()
        assertEquals(Pair("Years of Experience", Number), MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MInteger ok`(){
        val json = """
            { "Age": "Digit" }
        """.trimIndent()
        assertEquals(Pair("Age", Digit), MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition multiple values should return null`(){
        val json = """
            { "Name: "Text", "Age", "Digit" }
        """.trimIndent()
        assertNull(MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition invalid MType should fail`(){
        val json = """
            { "Name: "MText" }
        """.trimIndent()
        assertNull(MDefinition.parse(json))
    }

    @Test
    fun `test MDefinition MText ok without braces`(){
        val json = """
            "name": "Text"
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition MBoolean ok without braces`(){
        val json = """
             "Registered": "Flag" 
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition MNumber ok without braces`(){
        val json = """
            "Years of Experience": "Number"
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition MInteger ok without braces`(){
        val json = """
             "Age": "Digit" 
        """.trimIndent()
        assertEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition multiple values without braces should fail too`(){
        val json = """
             "Name: "Text", "Age", "Digit" 
        """.trimIndent()
        assertNotEquals(MDefinition, MDefinition.test(json))
        assertEquals(Text, MDefinition.test(json))
    }

    @Test
    fun `test MDefinition invalid MType should fail without braces`(){
        val json = """
             "Name: "MText" 
        """.trimIndent()
        assertNotEquals(MDefinition, MDefinition.test(json))
    }

    @Test
    fun `parse MDefinition MText ok without braces`(){
        val json = """
             "Name": "Text" 
        """.trimIndent()
        val expected = Pair("Name", Text)
        assertEquals(expected, MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MBoolean ok without braces`(){
        val json = """
            "Registered": "Flag"
        """.trimIndent()
        val expected = Pair("Registered", Flag)
        assertEquals(expected, MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MNumber ok without braces`(){
        val json = """
            "Years of Experience": "Number"
        """.trimIndent()
        val expected = Pair("Years of Experience", Number)
        assertEquals(expected, MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MInteger ok without braces`(){
        val json = """
             "Age": "Digit" 
        """.trimIndent()
        val expected = Pair("Age", Digit)
        assertEquals(expected, MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition multiple values without braces should return null`(){
        val json = """
            "Name: "Text", "Age", "Digit"
        """.trimIndent()
        assertNull(MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition invalid MType should fail without braces too`(){
        val json = """
             "Name: "MText"
        """.trimIndent()
        assertNull(MDefinition.parse(json))
    }
}
