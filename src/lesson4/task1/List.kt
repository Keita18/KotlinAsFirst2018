@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.digitNumber
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.fold(0.0) { prev, that ->
    prev + sqr(that)
})

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (!list.isEmpty()) (list.sum() / (list.size)) else 0.0

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val f = mean(list)
    for (i in 0 until list.size)
        list[i] = list[i] - f
    return list
}


/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var result = 0.0
    for (i in 0 until a.size) {
        result += a[i] * b[i]
    }
    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var result = 0.0
    var y = 1.0
    p.forEach { element ->
        result += element * y
        y *= x
    }
    return result
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    var result = 0.0
    for ((index, element) in list.withIndex()) {
        list[index] = result + element
        result += element

    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var element = n
    var d = 2
    val list = mutableListOf<Int>()
    while (element >= d) {
        if (element % d != 0) d++ else {
            list.add(d)
            element /= d
        }
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var number = n
    var d: Int
    val list = mutableListOf<Int>()
    if (n == 0) list.add(0)
    while (number >= 1) {
        d = number % base
        list.add(d)
        number /= base
    }
    return list.reversed()
}


/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    val convert = convert(n, base)
    val result = mutableListOf<String>()
    for (element in convert) {
        if (element < 10) {
            result.add(element.toString())
        } else result.add(('a' + element - 10).toString())
    }
    return result.joinToString(separator = "")
}


/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var need = 0
    var progress = 1
    for (i in 0 until digits.size) {
        need += digits.reversed()[i] * progress
        progress *= base
    }
    return need
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    var result = 1
    val list = mutableListOf<Int>()
    for (element in str) {
        if (element <= '9') list.add(element - '0') else list.add(element - 'a' + 10)
        result = decimal(list, base)
    }
    return result
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var x = n
    var z: Int
    val list = mutableListOf<String>()
    val list1 = mutableListOf<String>()
    while (x / 1000 < 4 && x > 0) {
        for (i in 0 until digitNumber(x)) {
            z = (x % 10) * (10.toDouble().pow(i).toInt())
            x /= 10
            list.add(intoRoman(z))
        }
    }
    while (x / 1000 > 4) {
        for (i in 0 until digitNumber(n) step 3) {
            z = x % 1000
            x /= 1000
            list.add(roman(z))
        }
    }
    for (element in list) {
        list1.add(element.reversed())
    }
    return list1.joinToString("").reversed()
}

fun intoRoman(n: Int): String {
    return when {
        n < 4 -> "I".repeat(n)
        n == 400 -> "CD"
        n == 4 -> "IV"
        n == 500 -> "D"
        n == 5 -> "V"
        n == 900 -> "CM"
        n in 10..39 step 10 -> "X".repeat(n / 10)
        n in 1000..3999 step 1000 -> "M".repeat(n / 1000)
        n == 40 -> "XL"
        n == 9 -> "IX"
        n == 50 -> "L"
        n == 90 -> "XC"
        n in 100..399 step 100 -> "C".repeat(n / 100)
        else -> when (n) {
            in 6..8 -> "V" + intoRoman(n - 5)
            in 60..90 step 10 -> "L" + intoRoman(n - 50)
            in 600..900 step 100 -> "D" + intoRoman(n - 500)
            else -> ""
        }
    }
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

val unit = listOf("", "один ", "два ", "три ", "четыре ", "пять ", "шесть ", "семь ", "восемь ", "девять ", "")
val unitOfThous = listOf("", "одна ", "две ", "три ", "четыре ", "пять ", "шесть ", "семь ", "восемь ", "девять ")
val dozens = listOf("", "одиннадцать ", "двенадцать ", "тринадцать ", "четырнадцать ", "пятнадцать ", "шестнадцать ",
        "семнадцать ", "восемнадцать ", "девятнадцать ", "")
val tens = listOf("", "десять ", "двадцать ", "тридцать ", "сорок ", "пятьдесят ", "шестьдесят ", "семьдесят ",
        "восемьдесят ", "девяносто ", "")
val hundreds = listOf("", "сто ", "двести ", "триста ", "четыреста ", "пятьсот ", "шестьсот ", "семьсот ", "восемьсот ",
        "девятьсот ", "")

fun russian(n: Int): String {
    var result = ""
    var answer = ""
    var x = n
    var a = x / 100 % 10
    var b = x % 100
    result += hundreds[a] + if (b in 11..19) dozens[b % 10] else tens[x / 10 % 10] + unit[x % 10]

    x /= 1000
    if (x > 0) {
        a = x / 100 % 10
        b = x % 100
        answer += hundreds[a] + (if (b in 11..19) dozens[b % 10] else tens[x / 10 % 10] + unitOfThous[x % 10]) +
                when {
                    x % 10 == 1 && b != 11 -> "тысяча "
                    x % 10 in 2..4 && b !in 12..14 -> "тысячи "
                    else -> "тысяч "
                }
    }

    return (answer + result).trim()

}

