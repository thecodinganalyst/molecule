package com.hevlar.molecule.core

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SeriesTest {

    @Test
    fun `MList can parse simple json list`(){
        val json = """
            ["a", "b", "c"]
        """.trimIndent()
        val expected = listOf(
            JsonPrimitive("a"),
            JsonPrimitive("b"),
            JsonPrimitive("c"),
        )
        assertEquals(expected, Series.parse(json))
    }

    @Test
    fun `MList parse return null if it is not json list`(){
        val json = """
            "a", "b", "c"
        """.trimIndent()
        assertNull(Series.parse(json))
    }

    @Test
    fun `MList parse list of objects ok`(){
        val json = """
            [
                {"name": "alex", "gender": "m", "age": 20 },
                {"name": "bernardine", "gender": "f", "age": 18 },
                {"name": "christopher", "gender": "m", "age": 24 }
            ]
        """.trimIndent()
        val expectedA = JsonObject()
        expectedA.addProperty("name", "alex")
        expectedA.addProperty("gender", "m")
        expectedA.addProperty("age", 20)
        val expectedB = JsonObject()
        expectedB.addProperty("name", "bernardine")
        expectedB.addProperty("gender", "f")
        expectedB.addProperty("age", 18)
        val expectedC = JsonObject()
        expectedC.addProperty("name", "christopher")
        expectedC.addProperty("gender", "m")
        expectedC.addProperty("age", 24)
        val expected = listOf(
            expectedA, expectedB, expectedC
        )
        assertEquals(expected, Series.parse(json))
    }

    @Test
    fun `json object parse as MList return null`(){
        val json = """
            {"name": "alex", "gender": "m", "age": 20 }
        """.trimIndent()
        assertNull(Series.parse(json))
    }

    @Test
    fun `simple text parse as MList return null`(){
        val json = "abc"
        assertNull(Series.parse(json))
    }

    @Test
    fun `MList test simple json list ok`(){
        val json = """
            ["a", "b", "c"]
        """.trimIndent()
        assertEquals(Series, Series.test(json))
    }

    @Test
    fun `MList test fail if it is not json list`(){
        val json = """
            "a", "b", "c"
        """.trimIndent()
        assertEquals(Text, Series.test(json))
    }

    @Test
    fun `MList test list of objects ok`(){
        val json = """
            [
                {"name": "alex", "gender": "m", "age": 20 },
                {"name": "bernardine", "gender": "f", "age": 18 },
                {"name": "christopher", "gender": "m", "age": 24 }
            ]
        """.trimIndent()
        assertEquals(Series, Series.test(json))
    }

    @Test
    fun `json object cannot pass test as MList`(){
        val json = """
            {"name": "alex", "gender": "m", "age": 20 }
        """.trimIndent()
        assertEquals(Text, Series.test(json))
    }

    @Test
    fun `simple text cannot pass test as MList`(){
        val json = "abc"
        assertEquals(Text, Series.test(json))
    }
}
