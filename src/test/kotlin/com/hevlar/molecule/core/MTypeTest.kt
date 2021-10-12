package com.hevlar.molecule.core

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

internal class MTypeTest {

    @Test
    fun `Parent shouldn't be null for MType declaration`(){
        assertThrows<Throwable> { MType("Test", null){ true } }
    }

    @Test
    fun testMText(){
        assertEquals(MText, MText.test("Anything"))
    }

    @Test
    fun testMNumber(){
        assertEquals(MNumber, MNumber.test("123.456"))
        assertEquals(MNumber, MNumber.test("123"))
        assertEquals(MText, MNumber.test("abc"))
    }

    @Test
    fun testBoolean(){
        assertEquals(MBoolean, MBoolean.test("true"))
        assertEquals(MBoolean, MBoolean.test("false"))
        assertEquals(MText, MBoolean.test("TRUE"))
        assertEquals(MText, MBoolean.test("FALSE"))
        assertEquals(MText, MBoolean.test("1"))
        assertEquals(MText, MBoolean.test("0"))
    }

    @Test
    fun testInteger(){
        assertEquals(MNumber, MInteger.test("123.456"))
        assertEquals(MInteger, MInteger.test("123"))
        assertEquals(MText, MInteger.test("abc"))
    }

    @Test
    fun `MTypeLibrary is ok`(){
        assertDoesNotThrow { MTypeLibrary }
    }
}
