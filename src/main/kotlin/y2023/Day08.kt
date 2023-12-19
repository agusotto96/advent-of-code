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
    for (instruction in wrappedInstructions(maps.instructions)) {
        steps++
        val key = if (instruction == 'L') node.first else node.second
        if (key == "ZZZ") {
            break
        }
        node = maps.nodes.getValue(key)
    }
    return steps
}

fun wrappedInstructions(instructions: List<Char>): Sequence<Char> {
    return sequence {
        while (true) {
            for (instruction in instructions) {
                yield(instruction)
            }
        }
    }
}
