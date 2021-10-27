package com.hevlar.molecule.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.BigInteger

internal class SeriesTest {

    @Test
    fun `MList can parse mix of series types`(){
        val json = """
            [1, 2.3, "c"]
        """.trimIndent()
        val expected = listOf(BigInteger("1"), BigDecimal("2.3"), "c")
        val actual = Series.parse(json)
        assertEquals(expected, actual)
    }

    @Test
    fun `MList can parse series of a series`(){
        val json = """
            [["a", "b", "c"], ["d", "e", "f"], ["g", "h", "i"]]
        """.trimIndent()
        val expected = listOf(
            listOf("a", "b", "c"),
            listOf("d", "e", "f"),
            listOf("g", "h", "i")
        )
        assertEquals(expected, Series.parse(json))
    }

    @Test
    fun `MList can parse simple json list`(){
        val json = """
            ["a", "b", "c"]
        """.trimIndent()
        val expected = listOf("a", "b", "c")
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
        val expectedA = mapOf(
            "name" to "alex", "gender" to "m", "age" to BigInteger("20")
        )
        val expectedB = mapOf(
            "name" to "bernardine", "gender" to "f", "age" to BigInteger("18")
        )
        val expectedC = mapOf(
            "name" to "christopher", "gender" to "m", "age" to BigInteger("24")
        )
        val expected = listOf(
            expectedA, expectedB, expectedC
        )
        val actual = Series.parse(json)
        assertEquals(expected, actual)
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
