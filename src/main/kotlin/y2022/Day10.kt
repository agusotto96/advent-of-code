package y2022

import java.io.File

sealed interface Instruction {
    data class AddX(val value: Int) : Instruction
    object Noop : Instruction
}

fun signalStrength(instructions: List<Instruction>): Int {
    var x = 1
    var strength = 0
    var cycle = 1
    for (instruction in instructions) {
        when (instruction) {
            is Instruction.AddX -> {
                cycle++
                if (cycle in setOf(20, 60, 100, 140, 180, 220)) {
                    strength += cycle * x
                }
                x += instruction.value
            }
            is Instruction.Noop -> {}
        }
        cycle++
        if (cycle in setOf(20, 60, 100, 140, 180, 220)) {
            strength += cycle * x
        }
    }
    return strength
}

fun instructions(file: File): List<Instruction> =
    file.readLines().map {
        if (it.startsWith("addx")) Instruction.AddX(it.split(" ").last().toInt())
        else if (it == "noop") Instruction.Noop
        else throw Exception()
    }
