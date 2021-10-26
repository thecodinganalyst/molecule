package com.hevlar.molecule.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class DataTest {

    @Test
    fun `MMap parse simple json object ok`(){
        val json = """
            {"name": "alex", "gender": "m", "age": 20, "registered": true, "locked": false }
        """.trimIndent()
        val expected = HashMap<String, Any>()
        expected["name"] = "alex"
        expected["gender"] = "m"
        expected["age"] = BigDecimal("20")
        expected["registered"] = true
        expected["locked"] = false
        val actual = Data.parse(json)
        assertNotNull(actual!!.keys)
        assertTrue(expected.keys == actual.keys)
        expected.keys.forEach { assertEquals(expected[it], actual[it]) }
    }

    @Test
    fun `MMap test simple json object ok`(){
        val json = """
            {"name": "alex", "gender": "m", "age": 20 }
        """.trimIndent()
        assertEquals(Data, Data.test(json))
    }

    @Test
    fun `Lists are not maps`(){
        val json = """
            ["a", "b", "c"]
        """.trimIndent()
        assertEquals(Text, Data.test(json))
    }


}
