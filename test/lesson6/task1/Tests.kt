package lesson6.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    @Test
    @Tag("Example")
    fun timeStrToSeconds() {
        assertEquals(36000, timeStrToSeconds("10:00:00"))
        assertEquals(41685, timeStrToSeconds("11:34:45"))
        assertEquals(86399, timeStrToSeconds("23:59:59"))
    }

    @Test
    @Tag("Example")
    fun twoDigitStr() {
        assertEquals("00", twoDigitStr(0))
        assertEquals("09", twoDigitStr(9))
        assertEquals("10", twoDigitStr(10))
        assertEquals("99", twoDigitStr(99))
    }

    @Test
    @Tag("Example")
    fun timeSecondsToStr() {
        assertEquals("10:00:00", timeSecondsToStr(36000))
        assertEquals("11:34:45", timeSecondsToStr(41685))
        assertEquals("23:59:59", timeSecondsToStr(86399))
    }

    @Test
    @Tag("Normal")
    fun dateStrToDigit( ) {
        assertEquals("15.07.2016", dateStrToDigit("15 июля 2016"))
        assertEquals("", dateStrToDigit("3 мартобря 1918"))
        assertEquals("18.11.2018", dateStrToDigit("18 ноября 2018"))
        assertEquals("", dateStrToDigit("23"))
        assertEquals("03.04.2011", dateStrToDigit("3 апреля 2011"))
        assertEquals("", dateStrToDigit("32 сентября 2011"))
        assertEquals("", dateStrToDigit("29 февраля 1993"))
    }

    @Test
    @Tag("Normal")
    fun dateDigitToStr() {
        assertEquals("15 июля 2016", dateDigitToStr("15.07.2016"))
        assertEquals("", dateDigitToStr("01.02.20.19"))
        assertEquals("", dateDigitToStr("28.00.2000"))
        assertEquals("3 апреля 2011", dateDigitToStr("03.04.2011"))
        assertEquals("", dateDigitToStr("ab.cd.ef"))
        assertEquals("", dateStrToDigit("32.09.2011"))
        assertEquals("", dateStrToDigit("29.02.1993"))
    }

    @Test
    @Tag("Normal")
    fun flattenPhoneNumber() {
        assertEquals("+79211234567", flattenPhoneNumber("+7 (921) 123-45-67"))
        assertEquals("123456798", flattenPhoneNumber("12 --  34- 5 -- 67 -98"))
        assertEquals("", flattenPhoneNumber("ab-123"))
        assertEquals("+12345", flattenPhoneNumber("+12 (3) 4-5"))
        assertEquals("", flattenPhoneNumber("134_+874"))
    }

    @Test
    @Tag("Normal")
    fun bestLongJump() {
        assertEquals(717, bestLongJump("706 % - 717 - 703"))
        assertEquals(-1, bestLongJump("% - - % -"))
        assertEquals(754, bestLongJump("700 717 707 % 754"))
        assertEquals(-1, bestLongJump("700 + 700"))
    }


    @Test
    @Tag("Hard")
    fun bestHighJump() {
        assertEquals(226, bestHighJump("226 +"))
        assertEquals(-1, bestHighJump("???"))
        assertEquals(230, bestHighJump("220 + 224 %+ 228 %- 230 + 232 %%- 234 %"))
        assertEquals(-1, bestHighJump("+ osiiaoaaoe e894 - - 456 +"))
    }

    @Test
    @Tag("Hard")
    fun plusMinus() {
        assertEquals(0, plusMinus("0"))
        assertEquals(4, plusMinus("2 + 2"))
        assertEquals(6, plusMinus("2 + 31 - 40 + 13"))
        assertEquals(-1, plusMinus("0 - 1"))
        assertThrows(IllegalArgumentException::class.java) { plusMinus("+2") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("+ 4") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("4 - -2") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("44 - - 12") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("4 - + 12") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("wZYb93%fDQDHK}t9Y@!o") }
    }

    @Test
    @Tag("Hard")
    fun firstDuplicateIndex() {
        assertEquals(-1, firstDuplicateIndex("Привет"))
        assertEquals(9, firstDuplicateIndex("Он пошёл в в школу"))
        assertEquals(40, firstDuplicateIndex("Яблоко упало на ветку с ветки оно упало на на землю"))
        assertEquals(9, firstDuplicateIndex("Мы пошли прямо Прямо располагался магазин"))
        assertEquals(0, firstDuplicateIndex("` ` C , u B P ( 3 U b \$ B . U ~ b ' ) q e S @ a o y q M r P { j K 9 d y [ p ^ q I + S F p i K G \\\\ : B 8 M 8 B h # V , c * \$ z j 3 { ^ A S ! b W X P N 0 v % Q Z m + 8 % j C X = : ~ > # { o J 7 V 6 O 4 q \$ T { 8 | \\\\ s o O 4 1 # C 2 t g ( 5 1 W 2 b k I q d ^ 3 , d x b ? C @ L 4 H U 1 g h q k @"))
        assertEquals(118, firstDuplicateIndex("( ? c q K U / w i ( i c > 4 ( 2 ; i h | ; > i } 5 l z b k % 6 ? ! ? d < Q Y G k _ f { F d } i } _ A e q ! # U @ w e t j j"))
    }

    @Test
    @Tag("Hard")
    fun mostExpensive() {
        assertEquals("", mostExpensive(""))
        assertEquals("Курица", mostExpensive("Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9"))
        assertEquals("Вино", mostExpensive("Вино 255.0"))
    }

    @Test
    @Tag("Hard")
    fun fromRoman() {
        assertEquals(1, fromRoman("I"))
        assertEquals(3000, fromRoman("MMM"))
        assertEquals(1978, fromRoman("MCMLXXVIII"))
        assertEquals(694, fromRoman("DCXCIV"))
        assertEquals(49, fromRoman("XLIX"))
        assertEquals(-1, fromRoman("Z"))
        assertEquals(7687, fromRoman("MMMMMMMDCLXXXVII"))
    }

    @Test
    @Tag("Impossible")
    fun computeDeviceCells() {
        assertEquals(listOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1), computeDeviceCells(10, "+>+>+>+>+", 10000))
        assertEquals(listOf(-1, -1, -1, -1, -1, 0, 0, 0, 0, 0), computeDeviceCells(10, "<-<-<-<-<-", 10000))
        assertEquals(listOf(1, 1, 1, 1, 1, 0, 0, 0, 0, 0), computeDeviceCells(10, "- <<<<< +[>+]", 10000))
        assertEquals(listOf(0, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0),
                computeDeviceCells(11, "<<<<< + >>>>>>>>>> --[<-] >+[>+] >++[--< <[<] >+[>+] >++]", 10000))

        assertEquals(listOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0), computeDeviceCells(10, "+>+>+>+>+", 4))
        assertEquals(listOf(0, 0, -1, -1, -1, 0, 0, 0, 0, 0), computeDeviceCells(10, "<-<-<-<-<-", 6))
        assertEquals(listOf(1, 1, 1, 0, 0, -1, 0, 0, 0, 0), computeDeviceCells(10, "- <<<<< +[>+]", 17))
        assertEquals(listOf(0, 6, 5, 4, 3, 2, 1, 0, -1, -1, -2),
                computeDeviceCells(11, "<<<<< + >>>>>>>>>> --[<-] >+[>+] >++[--< <[<] >+[>+] >++]", 256))
        assertThrows(IllegalArgumentException::class.java) { computeDeviceCells(10, "===", 3) }
        assertThrows(IllegalArgumentException::class.java) { computeDeviceCells(10, "+>+>[+>", 3) }
        assertThrows(IllegalStateException::class.java) { computeDeviceCells(20, ">>>>>>>>>>>>>", 12)  }
    }

    @Test
    @Tag("normal")
    fun ranking() {
        assertEquals(mapOf(
                "Zenit" to 9, "Real" to 6, "Barca" to 3, "Atletico" to 0)
                , ranking("Zenit-Real 4:1; Real-Barca 6:0; Zenit-Barca 3:2; Barca-Atletico 3:1; Real-Atletico 1:0; Zenit-Atletico 3:2"))
        assertEquals(mapOf("Zenit" to 3, "Barca" to 0, "Atletico" to 3), ranking("Zenit-Barca 3:2; Barca-Atletico 3:5"))
        assertThrows(IllegalArgumentException::class.java) { ranking("Zenit-Barca 3:2; Moscou-Zenit 1:0r") }
        assertThrows(IllegalArgumentException::class.java) { ranking("Real-Barca 3:2; MoscouZenit 1:0") }
        assertThrows(IllegalArgumentException::class.java) { ranking("Zenit-Barca 3:2; Moscou-Zenit 10")  }
    }

    @Test
    @Tag("Normal")
    fun uniqueHairColor() {
        assertEquals(
                mapOf("Nikita" to "C2C5C6",
                        "keita" to "B6FF18"), uniqueHairColor(listOf("Nikita #C2C5C6", "keita #B6FF18"  ))  )
    }
}

