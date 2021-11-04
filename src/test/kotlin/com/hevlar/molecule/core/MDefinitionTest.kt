package com.hevlar.molecule.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MDefinitionTest{

    @Test
    fun `complex definitions can test arrays as object`(){
        val propDef = MDefinition(listOf(
            MDefinition("type", MType.Companion),
            MDefinition("required", Flag),
            MDefinition("default", Text)
        ))
        val json = """
            ["Text", true, "hello world"]
        """.trimIndent()
        assertEquals(propDef, propDef.test(json))
    }

    @Test
    fun `definition can contain typeable as type`(){
        val propDef = MDefinition(listOf(
            MDefinition("type", MType.Companion),
            MDefinition("required", Flag),
            MDefinition("default", Text)
        ))
        val json = """
            {
              "type": "Text",
              "required": "true",
              "default": "hello world"
            }
        """.trimIndent()
        assertEquals(propDef, propDef.test(json))
    }

    @Test
    fun `test definition with key and value should pass`(){
        val dayDef = MDefinition("day", Digit)
        assertEquals(dayDef, dayDef.test("day", 12))
    }

    @Test
    fun `test definition with key and value should fail`(){
        val dayDef = MDefinition("day", Digit)
        assertNotEquals(dayDef, dayDef.test("day", "hello"))
    }

    @Test
    fun `read definition from data and test data adhere to definition`(){
        val dateJson = """
            {
              "day": "Digit",
              "month": "Digit",
              "year": "Digit"
            }
        """.trimIndent()
        val dateDef = MDefinition.parse(dateJson)
        assertNotNull(dateDef)
        val sampleDate1 = """
            {
              "day": 11,
              "month": 11,
              "year": 1982
            }
        """.trimIndent()
        assertEquals(dateDef, dateDef!!.test(sampleDate1))
    }

    @Test
    fun `read definition from data and test data adhere to definition should fail if doesnt match`(){
        val dateJson = """
            {
              "day": "Digit",
              "month": "Digit",
              "year": "Digit"
            }
        """.trimIndent()
        val dateDef = MDefinition.parse(dateJson)
        assertNotNull(dateDef)
        val sampleDate1 = """
            {
              "day": 11,
              "month": November,
              "year": 1982
            }
        """.trimIndent()
        assertNotEquals(dateDef, dateDef!!.test(sampleDate1))
        assertEquals(Data, dateDef.test(sampleDate1))
    }

    @Test
    fun `test MDefinition MText ok`(){
        val json = """
            { "name": "Text" }
        """.trimIndent()
        val def = MDefinition.test(json)
        assertTrue(def is MDefinition)
    }

    @Test
    fun `test MDefinition MBoolean ok`(){
        val json = """
            { "Registered": "Flag" }
        """.trimIndent()
        val def = MDefinition.test(json)
        assertTrue(def is MDefinition)
    }

    @Test
    fun `test MDefinition MNumber ok`(){
        val json = """
            { "Years of Experience": "Number" }
        """.trimIndent()
        val def = MDefinition.test(json)
        assertTrue(def is MDefinition)
    }

    @Test
    fun `test MDefinition MInteger ok`(){
        val json = """
            { "Age": "Digit" }
        """.trimIndent()
        val def = MDefinition.test(json)
        assertTrue(def is MDefinition)
    }

    @Test
    fun `test MDefinition multiple values should pass`(){
        val json = """
            { "Name": "Text", "Age": "Digit" }
        """.trimIndent()
        val def = MDefinition.test(json)
        assertTrue(def is MDefinition)
    }

    @Test
    fun `test MDefinition invalid MType should fail`(){
        val json = """
            { "Name": "MText" }
        """.trimIndent()
        val def = MDefinition.test(json)
        assertFalse(def is MDefinition)
        assertTrue(def is Data)
    }

    @Test
    fun `parse MDefinition MText ok`(){
        val json = """
            { "Name": "Text" }
        """.trimIndent()
        val parsedDef = MDefinition.parse(json)
        assertEquals(MDefinition("Name", Text), MDefinition.parse(json))
        assertEquals("Name", parsedDef!!.key)
        assertEquals(Text, parsedDef.type)
    }

    @Test
    fun `parse MDefinition MBoolean ok`(){
        val json = """
            { "Registered": "Flag" }
        """.trimIndent()
        assertEquals(MDefinition("Registered", Flag), MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MNumber ok`(){
        val json = """
            { "Years of Experience": "Number" }
        """.trimIndent()
        assertEquals(MDefinition("Years of Experience", Number), MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MInteger ok`(){
        val json = """
            { "Age": "Digit" }
        """.trimIndent()
        assertEquals(MDefinition("Age", Digit), MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition multiple values should return null`(){
        val json = """
            { "Name": "Text", "Age": "Digit" }
        """.trimIndent()
        assertEquals(MDefinition(),  MDefinition.parse(json))
        val def = MDefinition.parse(json)
        assertEquals(2, def!!.definitions.size)
        assertEquals("Name", def.definitions[0].key)
        assertEquals("Age", def.definitions[1].key)
        assertEquals(Text, def.definitions[0].type)
        assertEquals(Digit, def.definitions[1].type)
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
        assertEquals(MDefinition(), MDefinition.test(json))
    }

    @Test
    fun `test MDefinition MBoolean ok without braces`(){
        val json = """
             "Registered": "Flag" 
        """.trimIndent()
        assertEquals(MDefinition(), MDefinition.test(json))
    }

    @Test
    fun `test MDefinition MNumber ok without braces`(){
        val json = """
            "Years of Experience": "Number"
        """.trimIndent()
        assertEquals(MDefinition(), MDefinition.test(json))
    }

    @Test
    fun `test MDefinition MInteger ok without braces`(){
        val json = """
             "Age": "Digit" 
        """.trimIndent()
        assertEquals(MDefinition(), MDefinition.test(json))
    }

    @Test
    fun `test MDefinition multiple values without braces should pass too`(){
        val json = """
             "Name": "Text", "Age": "Digit" 
        """.trimIndent()
        assertEquals(MDefinition(), MDefinition.test(json))
    }

    @Test
    fun `test MDefinition invalid MType should fail without braces`(){
        val json = """
             "Name: "MText" 
        """.trimIndent()
        assertNotEquals(MDefinition(), MDefinition.test(json))
    }

    @Test
    fun `parse MDefinition MText ok without braces`(){
        val json = """
             "Name": "Text" 
        """.trimIndent()
        val expected = MDefinition("Name", Text)
        assertEquals(expected, MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MBoolean ok without braces`(){
        val json = """
            "Registered": "Flag"
        """.trimIndent()
        val expected = MDefinition("Registered", Flag)
        assertEquals(expected, MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MNumber ok without braces`(){
        val json = """
            "Years of Experience": "Number"
        """.trimIndent()
        val expected = MDefinition("Years of Experience", Number)
        assertEquals(expected, MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition MInteger ok without braces`(){
        val json = """
             "Age": "Digit" 
        """.trimIndent()
        val expected = MDefinition("Age", Digit)
        assertEquals(expected, MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition multiple values without braces should return MDefinition`(){
        val json = """
            "Name": "Text", "Age": "Digit"
        """.trimIndent()
        assertEquals(MDefinition() , MDefinition.parse(json))
    }

    @Test
    fun `parse MDefinition invalid MType should fail without braces too`(){
        val json = """
             "Name: "MText"
        """.trimIndent()
        assertNull(MDefinition.parse(json))
    }
}
