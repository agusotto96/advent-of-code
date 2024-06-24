package y2023

import java.io.File

data class Maps(
    val instructions: List<Char>,
    val nodes: Map<String, Pair<String, String>>,
)

fun Maps(file: File): Maps {
    val sections = file.readText().split("\n\n")
    val instructions = sections.first().toList()
    val nodes = sections.last()
        .split("\n")
        .filter(String::isNotBlank)
        .map { it.filter(Char::isLetter).chunked(3) }
        .associate { (key, left, right) -> key to Pair(left, right) }
    return Maps(instructions, nodes)
}

fun stepsToEnd(maps: Maps): Int {
    var steps = 0
    var node = maps.nodes.getValue("AAA")
    for ((_, instruction) in wrappedInstructions(maps.instructions)) {
        steps++
        val key = if (instruction == 'L') node.first else node.second
        if (key == "ZZZ") {
            break
        }
        node = maps.nodes.getValue(key)
    }
    return steps
}

fun ghostStepsToEnd(maps: Maps): Long =
    maps.nodes
        .asSequence()
        .filter { it.key.endsWith('A') }
        .map { it.value }
        .flatMap { ghostStepsToEnd(maps, it) }
        .reduce { acc, next -> findLCM(acc, next) }

fun ghostStepsToEnd(maps: Maps, startingNode: Pair<String, String>): Set<Long> {
    var steps = 0L
    var node = startingNode
    val endingSteps = mutableSetOf<Long>()
    val history = mutableSetOf<Pair<Int, String>>()
    for ((index, instruction) in wrappedInstructions(maps.instructions)) {
        steps++
        val key = if (instruction == 'L') node.first else node.second
        if (!history.add(index to key)) {
            break
        }
        if (key.endsWith('Z')) {
            endingSteps.add(steps)
        }
        node = maps.nodes.getValue(key)
    }
    return endingSteps
}

fun wrappedInstructions(instructions: List<Char>): Sequence<Pair<Int, Char>> {
    return sequence {
        while (true) {
            for ((index, instruction) in instructions.withIndex()) {
                yield(Pair(index, instruction))
            }
        }
    }
}

fun findLCM(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}
