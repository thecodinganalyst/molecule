package com.hevlar.molecule.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MMapTest {

    @Test
    fun `MMap can parse simple json object`(){
        val json = """
            {"name": "alex", "gender": "m", "age": 20 }
        """.trimIndent()
        assertEquals(MMap, MMap.test(json))
    }

    @Test
    fun `Lists are not maps`(){
        val json = """
            ["a", "b", "c"]
        """.trimIndent()
        assertEquals(MText, MMap.test(json))
    }


}