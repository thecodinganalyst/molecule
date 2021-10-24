package com.hevlar.molecule.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MMethodTest {

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
