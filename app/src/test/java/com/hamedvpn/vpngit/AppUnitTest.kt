package com.hamedvpn.vpngit

import com.hamedvpn.vpngit.enums.EConfigType
import com.hamedvpn.vpngit.util.CountryUtils
import com.hamedvpn.vpngit.util.Utils
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class AppUnitTest {

    @Test
    fun testParseIntDefaultAndValues() {
        assertEquals(443, Utils.parseInt("443", 80))
        assertEquals(80, Utils.parseInt("not-a-number", 80))
        assertEquals(80, Utils.parseInt(null, 80))
        assertEquals(0, Utils.parseInt("", 0))
    }

    @Test
    fun testArrayFind() {
        val array = arrayOf("US", "DE", "FR", "JP")
        assertEquals(0, Utils.arrayFind(array, "US"))
        assertEquals(2, Utils.arrayFind(array, "FR"))
        assertEquals(-1, Utils.arrayFind(array, "GB"))
    }

    @Test
    fun testFlagEmojiExtraction() {
        assertNull(CountryUtils.extractFlagEmoji(null))
        assertNull(CountryUtils.extractFlagEmoji(""))
        assertNull(CountryUtils.extractFlagEmoji("Plain server remarks without flag"))

        // US flag emoji: \uD83C\uDDFA\uD83C\uDDF8
        val usFlag = "\uD83C\uDDFA\uD83C\uDDF8"
        val remarksWithFlag = "US Server 01 $usFlag Fast"
        val extracted = CountryUtils.extractFlagEmoji(remarksWithFlag)
        assertNotNull(extracted)
        assertEquals(usFlag, extracted)
    }

    @Test
    fun testConfigTypeEnumValues() {
        val types = EConfigType.entries.map { it.name }
        assertTrue(types.contains("VMESS"))
        assertTrue(types.contains("VLESS"))
        assertTrue(types.contains("SHADOWSOCKS"))
        assertTrue(types.contains("TROJAN"))
        assertTrue(types.contains("SOCKS"))
        assertTrue(types.contains("WIREGUARD"))
        assertTrue(types.contains("HYSTERIA2"))
    }
}
