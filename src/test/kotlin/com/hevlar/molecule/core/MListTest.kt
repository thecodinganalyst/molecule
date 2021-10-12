package com.hevlar.molecule.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MListTest {

    @Test
    fun `MList can parse simple json list`(){
        val json = """
            ["a", "b", "c"]
        """.trimIndent()
        assertEquals(MList, MList.test(json))
    }

    @Test
    fun `MList will not parse if it is not json list`(){
        val json = """
            "a", "b", "c"
        """.trimIndent()
        assertEquals(MText, MList.test(json))
    }

    @Test
    fun `MList can parse list of objects`(){
        val json = """
            [
                {"name": "alex", "gender": "m", "age": 20 },
                {"name": "bernardine", "gender": "f", "age": 18 },
                {"name": "christopher", "gender": "m", "age": 24 }
            ]
        """.trimIndent()
        assertEquals(MList, MList.test(json))
    }

    @Test
    fun `json object cannot parse as MList`(){
        val json = """
            {"name": "alex", "gender": "m", "age": 20 }
        """.trimIndent()
        assertEquals(MText, MList.test(json))
    }

    @Test
    fun `simple text cannot parse as MList`(){
        val json = "abc"
        assertEquals(MText, MList.test(json))
    }
}