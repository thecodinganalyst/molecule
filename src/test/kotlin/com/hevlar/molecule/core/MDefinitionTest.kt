package com.hevlar.molecule.core

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MDefinitionTest {

    @Test
    fun testMDate() {
        val mDateDefinitionValue = mapOf(
            "day" to MInteger,
            "month" to MInteger,
            "year" to MInteger
        )
        val mDate = MDefinition("MDate", mDateDefinitionValue)

        val json = """
            {"day": "11", "month": "11", "year": "1982" }
        """.trimIndent()

        assertEquals(mDate, mDate.test(json))
    }

    @Test
    fun `testMDate with invalid values`() {
        val mDateDefinitionValue = mapOf(
            "day" to MInteger,
            "month" to MInteger,
            "year" to MInteger
        )
        val mDate = MDefinition("MDate", mDateDefinitionValue)

        val json = """
            {"day": "abc", "month": "11", "year": "1982" }
        """.trimIndent()

        assertEquals(MMap, mDate.test(json))
    }

    @Test
    fun `testMDate with insufficient values`() {
        val mDateDefinitionValue = mapOf(
            "day" to MInteger,
            "month" to MInteger,
            "year" to MInteger
        )
        val mDate = MDefinition("MDate", mDateDefinitionValue)

        val json = """
            {"day": "abc", "month": "11" }
        """.trimIndent()

        assertEquals(MMap, mDate.test(json))
    }

    @Test
    fun `testMDate with more than enough values`() {
        val mDateDefinitionValue = mapOf(
            "day" to MInteger,
            "month" to MInteger,
            "year" to MInteger
        )
        val mDate = MDefinition("MDate", mDateDefinitionValue)

        val json = """
            {"day": "11", "month": "11", "year": "1982", "hour": "12" }
        """.trimIndent()

        assertEquals(mDate, mDate.test(json))
    }
}
