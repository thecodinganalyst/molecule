package com.hevlar.molecule.core

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.math.BigInteger

internal class MTypeTest {

    @Test
    fun `Text equals Text`(){
        assertEquals(Text, Text)
    }

    @Test
    fun `Text doesn't equals Number`(){
        assertNotEquals(Text, Number)
    }

    @Test
    fun `Flag parse ok`(){
        assertEquals(true, Flag.parse("true"))
        assertEquals(false, Flag.parse("false"))
        assertNull(Flag.parse("True"))
        assertNull(Flag.parse("False"))
        assertNull(Flag.parse("TRUE"))
        assertNull(Flag.parse("FALSE"))
        assertNull(Flag.parse("T"))
        assertNull(Flag.parse("F"))
        assertNull(Flag.parse("t"))
        assertNull(Flag.parse("f"))
        assertNull(Flag.parse("1"))
        assertNull(Flag.parse("0"))
    }

    @Test
    fun `Digit parse ok`(){
        assertEquals(BigInteger("123"), Digit.parse("123"))
        assertEquals(BigInteger("0"), Digit.parse("0"))
        assertNull(Digit.parse(""))
        assertNull(Digit.parse("abc"))
    }

    @Test
    fun `Number parse ok`(){
        assertEquals(BigDecimal("123.321"), Number.parse("123.321"))
        assertEquals(BigDecimal("123"), Number.parse("123"))
        assertEquals(BigDecimal("0"), Number.parse("0"))
        assertNull(Number.parse(""))
        assertNull(Number.parse("abc"))
    }

    @Test
    fun `Text parse ok`(){
        assertEquals("hello", Text.parse("hello"))
        assertEquals("", Text.parse(""))
    }

    @Test
    fun `MTypeLibrary doesn't allow registering types which already exists`(){
        assertFalse(MTypeLibrary.register(MType("Number", Text) { true }))
    }

    @Test
    fun `MTypeLibrary can register new type`(){
        assertTrue(MTypeLibrary.register(MType("TestType", Text) { true }))
    }

    @Test
    fun `MTypeLibrary contains Text, Number, Flag, Digit`(){
        assertNotNull(MTypeLibrary.get("Text"))
        assertNotNull(MTypeLibrary.get("Number"))
        assertNotNull(MTypeLibrary.get("Flag"))
        assertNotNull(MTypeLibrary.get("Digit"))
    }

    @Test
    fun `Parent shouldn't be null for MType declaration`(){
        assertThrows<Throwable> { MType("Test", null){ true } }
    }

    @Test
    fun testText(){
        assertEquals(Text, Text.test("Anything"))
    }

    @Test
    fun testNumber(){
        assertEquals(Number, Number.test("123.456"))
        assertEquals(Number, Number.test("123"))
        assertEquals(Text, Number.test("abc"))
    }

    @Test
    fun testFlag(){
        assertEquals(Flag, Flag.test("true"))
        assertEquals(Flag, Flag.test("false"))
        assertEquals(Text, Flag.test("TRUE"))
        assertEquals(Text, Flag.test("FALSE"))
        assertEquals(Text, Flag.test("1"))
        assertEquals(Text, Flag.test("0"))
    }

    @Test
    fun testDigit(){
        assertEquals(Number, Digit.test("123.456"))
        assertEquals(Digit, Digit.test("123"))
        assertEquals(Text, Digit.test("abc"))
    }

    @Test
    fun `MTypeLibrary is ok`(){
        assertDoesNotThrow { MTypeLibrary }
    }
}
