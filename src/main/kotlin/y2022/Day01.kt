package y2022

import java.io.File

fun elvesCalories(file: File): List<List<Int>> {
    val elvesCalories = mutableListOf<List<Int>>()
    var elfCalories = mutableListOf<Int>()
    for (line in file.readLines()) {
        if (line.isNotEmpty()) {
            val calories = line.toInt()
            elfCalories += calories
        } else {
            elvesCalories += elfCalories
            elfCalories = mutableListOf()
        }
    }
    elvesCalories += elfCalories
    return elvesCalories
}

fun maxElfCaloriesSum(elvesCalories: List<List<Int>>, count: Int = 1): Int =
    elvesCalories
        .map(Iterable<Int>::sum)
        .sorted()
        .takeLast(count)
        .sum()