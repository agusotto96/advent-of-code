package y2021

import java.io.File

fun simulateGrowth(initialPopulation: Map<Int, Long>, days: Int): Long {
    val population = initialPopulation.toMutableMap()
    repeat(days) {
        val births = population[0] ?: 0
        population[0] = 0
        for (age in 1..8) {
            population[age - 1] = population[age] ?: 0
        }
        population[6] = (population[6] ?: 0) + births
        population[8] = births
    }
    return population.values.sum()
}

fun population(file: File): Map<Int, Long> {
    val population = mutableMapOf(*(0..8).map { it to 0L }.toTypedArray())
    val ages = file.readText().split(",").map(String::toInt)
    for (age in ages) population[age] = (population[age] ?: 0) + 1
    return population
}