package com.hevlar.molecule.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MPropertyTest{

    @Test
    fun `parse property as list ok`(){
        val json = """
            [ Text, "hello", false ]
        """.trimIndent()
        val prop = MProperty.parse(json) as MProperty
        assertEquals(MProperty(), prop)
        assertEquals(Text, prop.type)
        assertEquals("hello", prop.default)
        assertEquals(false, prop.required)
    }

    @Test
    fun `parse property ok`(){
        val json = """
            { type: Text, default: "hello", required: false }
        """.trimIndent()
        val prop = MProperty.parse(json) as MProperty
        assertEquals(MProperty(), prop)
        assertEquals(Text, prop.type)
        assertEquals("hello", prop.default)
        assertEquals(false, prop.required)
    }

    @Test
    fun `parse property ok when required is absent`(){
        val json = """
            { type: Text, default: "hello" }
        """.trimIndent()
        val prop = MProperty.parse(json) as MProperty
        assertEquals(MProperty(), prop)
        assertEquals(Text, prop.type)
        assertEquals("hello", prop.default)
        assertEquals(true, prop.required)
    }

    @Test
    fun `parse property ok when default is absent`(){
        val json = """
            { type: Text, required: false }
        """.trimIndent()
        val prop = MProperty.parse(json) as MProperty
        assertEquals(MProperty(), prop)
        assertEquals(Text, prop.type)
        assertEquals(false, prop.required)
    }

    @Test
    fun `parse property ok when only type is present`(){
        val json = """
            { type: Text }
        """.trimIndent()
        val prop = MProperty.parse(json) as MProperty
        assertEquals(MProperty(), prop)
        assertEquals(Text, prop.type)
    }

    @Test
    fun `test property created ok`(){
        val json = """
            { type: Text, default: "hello", required: false }
        """.trimIndent()
        assertEquals(MProperty(), MProperty.test(json))
    }

    @Test
    fun `test property created ok when default is not available`(){
        val json = """
            { type: Text, required: false }
        """.trimIndent()
        assertEquals(MProperty(), MProperty.test(json))
    }

    @Test
    fun `test property created ok when required is not available`(){
        val json = """
            { type: Text, default: "hello" }
        """.trimIndent()
        assertEquals(MProperty(), MProperty.test(json))
    }

    @Test
    fun `test property should fail if default is not correct type`(){
        val json = """
            { type: Flag, default: "hello", required: false }
        """.trimIndent()
        assertNotEquals(MProperty(), MProperty.test(json))
        assertEquals(Data, MProperty.test(json))
    }

}
