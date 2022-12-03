package y2022

import java.io.File

typealias RawIndication = Pair<Char, Char>

typealias AssumedIndication = Pair<Shape, Shape>

typealias TrueIndication = Pair<Shape, Outcome>

enum class Shape {
    Rock, Paper, Scissor
}

enum class Outcome {
    Lose, Draw, Win
}

fun rawIndications(file: File): List<RawIndication> =
    file.readLines()
        .map { it.split(" ") }
        .map { it.first().single() to it.last().single() }

fun assumedIndications(indications: List<Pair<Char, Char>>): List<AssumedIndication> =
    indications.map { Shape(it.first) to Shape(it.second) }

fun trueIndications(indications: List<Pair<Char, Char>>): List<TrueIndication> =
    indications.map { Shape(it.first) to Outcome(it.second) }

fun trueIndicationPoints(trueIndication: TrueIndication): Int =
    Shape(trueIndication).let(::shapePoints) + outcomePoints(trueIndication.second)

fun trueIndicationsPoints(trueIndications: List<TrueIndication>): Int =
    trueIndications.map(::trueIndicationPoints).sum()

fun Shape(trueIndication: TrueIndication): Shape =
    when (trueIndication.first) {
        Shape.Rock -> when (trueIndication.second) {
            Outcome.Lose -> Shape.Scissor
            Outcome.Draw -> Shape.Rock
            Outcome.Win -> Shape.Paper
        }
        Shape.Paper -> when (trueIndication.second) {
            Outcome.Lose -> Shape.Rock
            Outcome.Draw -> Shape.Paper
            Outcome.Win -> Shape.Scissor
        }
        Shape.Scissor -> when (trueIndication.second) {
            Outcome.Lose -> Shape.Paper
            Outcome.Draw -> Shape.Scissor
            Outcome.Win -> Shape.Rock
        }
    }

fun Shape(symbol: Char): Shape =
    when (symbol) {
        'A' -> Shape.Rock
        'B' -> Shape.Paper
        'C' -> Shape.Scissor
        'X' -> Shape.Rock
        'Y' -> Shape.Paper
        'Z' -> Shape.Scissor
        else -> throw IllegalArgumentException()
    }

fun Outcome(symbol: Char): Outcome =
    when (symbol) {
        'X' -> Outcome.Lose
        'Y' -> Outcome.Draw
        'Z' -> Outcome.Win
        else -> throw IllegalArgumentException()
    }

fun shapePoints(shape: Shape): Int =
    when (shape) {
        Shape.Rock -> 1
        Shape.Paper -> 2
        Shape.Scissor -> 3
    }

fun outcomePoints(outcome: Outcome): Int =
    when (outcome) {
        Outcome.Lose -> 0
        Outcome.Draw -> 3
        Outcome.Win -> 6
    }

fun Outcome(assumedIndication: AssumedIndication): Outcome =
    when (assumedIndication.first) {
        Shape.Rock -> when (assumedIndication.second) {
            Shape.Rock -> Outcome.Draw
            Shape.Paper -> Outcome.Win
            Shape.Scissor -> Outcome.Lose
        }
        Shape.Paper -> when (assumedIndication.second) {
            Shape.Rock -> Outcome.Lose
            Shape.Paper -> Outcome.Draw
            Shape.Scissor -> Outcome.Win
        }
        Shape.Scissor -> when (assumedIndication.second) {
            Shape.Rock -> Outcome.Win
            Shape.Paper -> Outcome.Lose
            Shape.Scissor -> Outcome.Draw
        }
    }

fun assumedIndicationPoints(assumedIndication: AssumedIndication): Int =
    shapePoints(assumedIndication.second) + Outcome(assumedIndication).let(::outcomePoints)

fun assumedIndicationsPoints(assumedIndications: List<AssumedIndication>): Int =
    assumedIndications.map(::assumedIndicationPoints).sum()