@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
/*fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}
*/


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
val months = listOf("января", "февраля", "марта", "апреля",
        "мая", "июня", "июля", "августа", "сентября",
        "октября", "ноября", "декабря")

fun dateStrToDigit(str: String): String {
    val time = str.split(" ")
    if (time.size != 3 || (time[1] !in months))
        return ""

    val day = time[0].toInt()
    val year = time[2].toInt()
    val month = months.indexOf(time[1]) + 1

    if (day > 31 || day < 1 || (daysInMonth(month, year) < day))
        return ""


    return String.format("%02d.%02d.%d", day, month, year)

}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val time = digital.split(".")
    if (time.size != 3) return ""

    return try {
        val day = time[0].toInt()
        val year = time[2].toInt()
        val month = time[1].toInt()

        if (day !in 1..31 || month !in 1..12)
            throw NumberFormatException()

        val months = months[month - 1]

        if (daysInMonth(month, year) < day)
            throw NumberFormatException()

        String.format("%d %s %d", day, months, year)
    } catch (e: NumberFormatException) {
        ""
    }
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String =
        if (!phone.matches(Regex("""^(\+\s*\d)?([\d\s\-)(])*"""))) ""
        else phone.filter { it == '+' || it.isDigit() }


/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val parts = jumps.split(" ").filter { it != "%" && it != "-" && it != " " }.toSet()
    val list = mutableListOf<Int>()

    val firstDigit = jumps.filter { it.isDigit() }
    val secondDigit = jumps.filter { it != '%' && it != '-' && it != ' ' }

    for (elem in parts)
        if (elem.all { elemChar -> "1234567890".contains(elemChar) } && elem != "")
            list.add(elem.toInt())

    var bestTry = -1

    if (list.max() != null && firstDigit == secondDigit && firstDigit.isNotEmpty())
        bestTry = list.toSet().max()!!

    return bestTry
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    if (!jumps.all { itChar -> "0123456789-+% ".contains(itChar) })
        return -1
    val jump = jumps.split(" ")
    var ans = ""

    for (i in 1 until jump.size)
        if (jump[i].contains("+") && jump.size > 1) {
            ans += " " + jump[i - 1]
        }

    return when {
        ans != "" -> bestLongJump(ans)
        else -> -1
    }
}


/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    if (!expression.matches(Regex("""^\d+( [-+] \d+)*""")))
        throw IllegalArgumentException()

    val list = expression.split(" ")
    var number: Int
    var sign = "+"
    var sum = 0
    var i = 0

    while (list.size > i) {

        number = list[i].toInt()

        sum += when (sign) {
            "+" -> number
            "-" -> -number
            else -> 0
        }

        if (i == list.size - 1) break
        sign = list[i + 1]

        i += 2
    }
    return sum
}


/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val list = str.split(" ")
    var res = 0
    for (i in 0 until (list.size - 1)) {
        if (list[i].toLowerCase() == list[i + 1].toLowerCase())
            return res
        res += list[i].length + 1
    }
    return -1
}


/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String =
        if (!Regex("""(?:\S+ \d+(?:\.\d+)?)(?:; \S+ \d+(?:\.\d+)?)*""").matches(description)) ""
        else description.split("; ")
                .map { it.split(' ')[0] to it.split(' ')[1].toDouble() }
                .toMap().maxBy { it.value }?.key + ""


/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    if (!roman.all { itChar -> "IVXLCDM".contains(itChar) })
        return -1
    val romanList = roman.split("").reversed()
    val numberList = mutableListOf<Int>()
    var i = 0
    var ans = 0

    while (i < romanList.size - 1) {
        val res = when (romanList[i]) {
            "M" -> 1000
            "D" -> 500
            "C" -> 100
            "L" -> 50
            "X" -> 10
            "V" -> 5
            "I" -> 1
            else -> 0
        }
        numberList.add(res)
        i++
    }

    var number: Int
    var sign = "+"

    for (j in 0 until numberList.size) {
        number = numberList[j]

        ans += when (sign) {
            "+" -> number
            "-" -> -number
            else -> 0
        }
        if (j == numberList.size - 1) break
        sign = if (numberList[j] <= numberList[j + 1]) "+"
        else "-"
    }

    return if (ans != 0) ans else -1
}


/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */

fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {

    if (!commands.all { itChar -> setOf(' ', '>', '<', '+', '-', '[', ']').contains(itChar) })
        throw IllegalArgumentException()
    if (commands.filter { it == '[' }.count() != commands.filter { it == ']' }.count())
        throw IllegalArgumentException()

    val cellsArray = Array(cells) { 0 }
    var position = cells / 2
    var curCommand = 0
    var i = 0

    while (curCommand < limit) {
        if (i >= commands.length) break
        var sealer = 1 // :)
        when (commands[i]) {
            '>' -> position++
            '<' -> position--
            '+' -> cellsArray[position]++
            '-' -> cellsArray[position]--
            '[' -> {
                if (cellsArray[position] == 0) {

                    while (sealer > 0) {
                        i++

                        if (commands[i] == '[')
                            sealer++
                        else if (commands[i] == ']')
                            sealer--
                    }
                }
            }
            ']' -> {
                if (cellsArray[position] != 0) {

                    while (sealer > 0) {
                        i--
                        if (commands[i] == ']')
                            sealer++
                        else if (commands[i] == '[')
                            sealer--
                    }
                }
            }
            else -> {
            }
        }

        i++
        curCommand++
        if (position !in 0 until cells) throw IllegalStateException()
    }
    return cellsArray.toList()
}

fun main(args: Array<String>) {
    val x = computeDeviceCells(1, "+-++[<--[++-<---<- --[+<-]-]+>- +----[]-<+<->- <-> -<++< -+-+[<--< ---++<<--<-<-+-+>-+>++]<---<--+->-><+<-<-", 0)
    println(x)
}
fun ranking(text: String): MutableMap<String, Int> {
    if (!Regex("""^(?:\S+[-]\S+ \d+(?:[:]\d+))(?:; \S+[-]\S+ \d+(?:[:]\d+))*""").matches(text))
        throw IllegalArgumentException()

    val result = mutableMapOf<String, MutableList<Int>>()
    for (element in text.split("; ")) {
        val score = element.split(" ")[1].split(":")
        val winnerLoser = element.split(" ")[0].split("-")

        when {
            score[0].toInt() > score[1].toInt() -> {
                result.getOrPut(winnerLoser[0], ::mutableListOf).add(3) // winner
                result.getOrPut(winnerLoser[1], ::mutableListOf).add(0) // loser
            }
            score[0].toInt() == score[1].toInt() -> {

                result.getOrPut(winnerLoser[0], ::mutableListOf).add(1)
                result.getOrPut(winnerLoser[1], ::mutableListOf).add(1)
            }
            score[0].toInt() < score[1].toInt() -> {
                result.getOrPut(winnerLoser[1], ::mutableListOf).add(3)
                result.getOrPut(winnerLoser[0], ::mutableListOf).add(0)
            }
        }

    }
    val rank = mutableMapOf<String, Int>()
    for ((key, value) in result)
        rank[key] = value.sum()
    return rank

}


fun uniqueHairColor(people: List<String>): Map<String, String> {
    val result = mutableMapOf<String, MutableList<String>>()

    people.forEach { element ->

        if (!Regex("""(\S+ [#](\d+|[A-F])+)""").matches(element))
            throw IllegalArgumentException()

        val color = element.split(" ")[1].removePrefix("#")
        val name = element.split(" ")[0]
        if (color.count() > 6)
            throw IllegalArgumentException()

        result.getOrPut(color, ::mutableListOf).add(name)
    }

    return result.filter { it.value.size == 1 }.map { it.value.first() to it.key }.toMap()
}












