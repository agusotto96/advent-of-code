package y2021

import java.io.File

val uniquePatternSizes = setOf(2, 4, 3, 7)

data class Display(val patterns: List<Segments>, val digits: List<Segments>)

data class Segments(val segments: Set<Char>) {
    fun size() = segments.size
    fun contains(other: Segments) = segments.containsAll(other.segments)
    operator fun minus(other: Segments) = Segments(segments - other.segments)
}

fun displays(file: File): List<Display> {
    return file.readLines().map(::Display)
}

fun Display(input: String): Display {
    val splitInput = input.split(" | ")
    val segments = splitInput.first().split(" ").map(String::toSet).map(::Segments)
    val digits = splitInput.last().split(" ").map(String::toSet).map(::Segments)
    return Display(segments, digits)
}

fun uniqueSegmentNumberDigits(entries: List<Display>): Int {
    return entries.flatMap(Display::digits).map(Segments::size).count(uniquePatternSizes::contains)
}

fun displaysOutputSum(displays: List<Display>): Int {
    return displays.map(::output).sum()
}

fun output(display: Display): Int {
    val digitsByPattern = digitsByPattern(display.patterns)
    return display.digits.map(digitsByPattern::getValue).joinToString("", transform = Int::toString).toInt()
}

fun digitsByPattern(patterns: List<Segments>): Map<Segments, Int> {
    val patternsBySize = patterns.groupBy(Segments::size)
    val one = patternsBySize.getValue(2).single()
    val four = patternsBySize.getValue(4).single()
    val seven = patternsBySize.getValue(3).single()
    val eight = patternsBySize.getValue(7).single()
    val twoThreeFive = patternsBySize.getValue(5)
    val zeroSixNine = patternsBySize.getValue(6)
    val three = twoThreeFive.single { it.contains(one) }
    val nine = zeroSixNine.single { (it - three).size() == 1 }
    val six = (zeroSixNine - nine).single { a -> (twoThreeFive - three).any { b -> (a - b).size() == 1 } }
    val five = (twoThreeFive - three).single { (six - it).size() == 1 }
    val two = (twoThreeFive - three - five).single()
    val zero = (zeroSixNine - six - nine).single()
    return mapOf(
        zero to 0, one to 1, two to 2, three to 3, four to 4,
        five to 5, six to 6, seven to 7, eight to 8, nine to 9
    )
}
