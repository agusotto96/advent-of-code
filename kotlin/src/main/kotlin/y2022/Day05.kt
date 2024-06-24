package y2022

import java.io.File

typealias CrateStack = List<Char>

data class RearrangementStep(val quantity: Int, val from: Int, val to: Int)

data class DisarrangedCrane(val crateStacks: Map<Int, CrateStack>, val rearrangementSteps: List<RearrangementStep>)

data class RearrangedCrane(val crateStacks: Map<Int, CrateStack>)

fun RearrangedCrane.topCrates(): CrateStack =
    crateStacks.values.map(CrateStack::first)

fun completeRearrangement(crane: DisarrangedCrane, craneModel: CraneModel): RearrangedCrane {
    val crateStacks = crane.crateStacks.toMutableMap()
    for (step in crane.rearrangementSteps) {
        val selectedCrates = craneModel.selectCrates(crateStacks.getValue(step.from), step.quantity)
        crateStacks[step.from] = crateStacks.getValue(step.from).drop(step.quantity)
        crateStacks[step.to] = selectedCrates + crateStacks.getValue(step.to)
    }
    return RearrangedCrane(crateStacks)
}

fun interface SelectCrates {
    fun selectCrates(crateStack: CrateStack, quantity: Int): CrateStack
}

enum class CraneModel : SelectCrates {
    CrateMover9000 {
        override fun selectCrates(crateStack: CrateStack, quantity: Int): CrateStack =
            crateStack.take(quantity).reversed()
    },
    CrateMover9001 {
        override fun selectCrates(crateStack: CrateStack, quantity: Int): CrateStack =
            crateStack.take(quantity)
    }
}

fun DisarrangedCrane(file: File): DisarrangedCrane {
    val inputLines = file.readLines()
    val (crateStacksLines, rearrangementStepsLines) = splitCraneInputLines(inputLines)
    val crateStacks = crateStacks(crateStacksLines)
    val rearrangementSteps = rearrangementSteps(rearrangementStepsLines)
    return DisarrangedCrane(crateStacks, rearrangementSteps)
}

fun splitCraneInputLines(lines: List<String>): Pair<List<String>, List<String>> {
    val crateStacksLines = mutableListOf<String>()
    val rearrangementStepsLines = mutableListOf<String>()
    var reachedEmptyLine = false
    for (line in lines) {
        if (line.isEmpty()) {
            reachedEmptyLine = true
        } else {
            if (reachedEmptyLine) {
                rearrangementStepsLines += line
            } else {
                crateStacksLines += line
            }
        }
    }
    return Pair(crateStacksLines, rearrangementStepsLines)
}

fun crateStacks(lines: List<String>): Map<Int, CrateStack> {
    var stack = mutableListOf<Char>()
    val stacks = mutableMapOf<Int, List<Char>>()
    val idsLine = lines.last()
    val cratesLine = lines.dropLast(1)
    for (i in 1..lines.first().length step 4) {
        val id = idsLine[i].digitToInt()
        for (line in cratesLine) {
            val crate = line[i]
            if (crate != ' ') {
                stack += crate
            }
        }
        stacks[id] = stack
        stack = mutableListOf()
    }
    return stacks
}

fun rearrangementSteps(lines: List<String>): List<RearrangementStep> =
    lines
        .map { it.removePrefix("move ") }
        .map { it.split(" from ", " to ") }
        .map { it.map(String::toInt) }
        .map { RearrangementStep(it[0], it[1], it[2]) }
