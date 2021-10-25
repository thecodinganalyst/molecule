package com.hevlar.molecule.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MMethodTest {

    @Test
    fun `test method should return MMap if invalid`(){
        val json = """
            {
                "inputs": {
                    "a": "Number",
                    "b": "MNumber"
                },
                "output": "MNumber"
            }
        """.trimIndent()
        assertEquals(MMap, MMethod.test(json))
    }

    @Test
    fun testAddMethod(){
        val json = """
            {
                "inputs": {
                    "a": "MNumber",
                    "b": "MNumber"
                },
                "output": "MNumber"
            }
        """.trimIndent()
        assertEquals(MMethod, MMethod.test(json))
    }

}
