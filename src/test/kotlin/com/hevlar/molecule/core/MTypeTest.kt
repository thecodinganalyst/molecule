package com.hevlar.molecule.core

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

internal class MTypeTest {

    @Test
    fun `MTypeLibrary doesn't allow registering types which already exists`(){
        assertFalse(MTypeLibrary.register(MType("MNumber", MText) { true }))
    }

    @Test
    fun `MTypeLibrary can register new type`(){
        assertTrue(MTypeLibrary.register(MType("TestType", MText) { true }))
    }

    @Test
    fun `MTypeLibrary contains MText, MNumber, MBoolean, MInteger`(){
        assertNotNull(MTypeLibrary.get("MText"))
        assertNotNull(MTypeLibrary.get("MNumber"))
        assertNotNull(MTypeLibrary.get("MBoolean"))
        assertNotNull(MTypeLibrary.get("MInteger"))
    }

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
