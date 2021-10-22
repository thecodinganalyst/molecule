package com.hevlar.molecule.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MDefinitionTest{

    @Test
    fun `test MDefinition ok`(){
        val json = """
            { "name": "Name", "type": "MText" }
        """.trimIndent()
        val expected = MDefinition("Name", MText)
        assertEquals(expected, expected.test(json))
    }
}
