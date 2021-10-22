package com.hevlar.molecule.core

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.math.BigInteger

internal class MTypeTest {

    @Test
    fun `MBoolean parse ok`(){
        assertEquals(true, MBoolean.parse("true"));
        assertEquals(false, MBoolean.parse("false"));
        assertNull(MBoolean.parse("True"));
        assertNull(MBoolean.parse("False"));
        assertNull(MBoolean.parse("TRUE"));
        assertNull(MBoolean.parse("FALSE"));
        assertNull(MBoolean.parse("T"));
        assertNull(MBoolean.parse("F"));
        assertNull(MBoolean.parse("t"));
        assertNull(MBoolean.parse("f"));
        assertNull(MBoolean.parse("1"));
        assertNull(MBoolean.parse("0"));
    }

    @Test
    fun `MInteger parse ok`(){
        assertEquals(BigInteger("123"), MInteger.parse("123"));
        assertEquals(BigInteger("0"), MInteger.parse("0"));
        assertNull(MInteger.parse(""));
        assertNull(MInteger.parse("abc"));
    }

    @Test
    fun `MNumber parse ok`(){
        assertEquals(BigDecimal("123.321"), MNumber.parse("123.321"));
        assertEquals(BigDecimal("123"), MNumber.parse("123"));
        assertEquals(BigDecimal("0"), MNumber.parse("0"));
        assertNull(MNumber.parse(""));
        assertNull(MNumber.parse("abc"));
    }

    @Test
    fun `MText parse ok`(){
        assertEquals("hello", MText.parse("hello"));
        assertEquals("", MText.parse(""));
    }

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
