package y2022

import java.io.File

typealias ElfGroup = Triple<Rucksack, Rucksack, Rucksack>

typealias Rucksack = Pair<Compartment, Compartment>

typealias Compartment = List<Item>

sealed interface Item

enum class Lower : Item { A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z }

enum class Upper : Item { A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z }

fun Item(symbol: Char): Item =
    when (symbol) {
        'a' -> Lower.A
        'b' -> Lower.B
        'c' -> Lower.C
        'd' -> Lower.D
        'e' -> Lower.E
        'f' -> Lower.F
        'g' -> Lower.G
        'h' -> Lower.H
        'i' -> Lower.I
        'j' -> Lower.J
        'k' -> Lower.K
        'l' -> Lower.L
        'm' -> Lower.M
        'n' -> Lower.N
        'o' -> Lower.O
        'p' -> Lower.P
        'q' -> Lower.Q
        'r' -> Lower.R
        's' -> Lower.S
        't' -> Lower.T
        'u' -> Lower.U
        'v' -> Lower.V
        'w' -> Lower.W
        'x' -> Lower.X
        'y' -> Lower.Y
        'z' -> Lower.Z
        'A' -> Upper.A
        'B' -> Upper.B
        'C' -> Upper.C
        'D' -> Upper.D
        'E' -> Upper.E
        'F' -> Upper.F
        'G' -> Upper.G
        'H' -> Upper.H
        'I' -> Upper.I
        'J' -> Upper.J
        'K' -> Upper.K
        'L' -> Upper.L
        'M' -> Upper.M
        'N' -> Upper.N
        'O' -> Upper.O
        'P' -> Upper.P
        'Q' -> Upper.Q
        'R' -> Upper.R
        'S' -> Upper.S
        'T' -> Upper.T
        'U' -> Upper.U
        'V' -> Upper.V
        'W' -> Upper.W
        'X' -> Upper.X
        'Y' -> Upper.Y
        'Z' -> Upper.Z
        else -> throw Exception()
    }

fun rucksacks(file: File): List<Rucksack> =
    file.readLines()
        .map(String::toList)
        .map { it.map(::Item) }
        .map { it.subList(0, it.size / 2) to it.subList(it.size / 2, it.size) }

fun elfGroups(file: File): List<ElfGroup> =
    file.let(::rucksacks).let(::elfGroups)

fun elfGroups(rucksacks: List<Rucksack>): List<ElfGroup> =
    rucksacks.chunked(3).map { Triple(it[0], it[1], it[2]) }

fun repeatedItem(rucksack: Rucksack): Item =
    repeatedItem(rucksack.toList())

fun repeatedItem(elfGroup: ElfGroup): Item =
    repeatedItem(elfGroup.toList().map(Rucksack::items))

fun Rucksack.items(): List<Item> =
    first + second

fun repeatedItem(items: Iterable<Iterable<Item>>): Item =
    items.reduce { a, b -> a.intersect(b.toSet()) }.first()

fun rucksacksPrioritySum(rucksacks: List<Rucksack>): Int =
    rucksacks.map(::repeatedItem).map(::priority).sum()

fun elfGroupsPrioritySum(elfGroups: List<ElfGroup>): Int =
    elfGroups.map(::repeatedItem).map(::priority).sum()

fun priority(item: Item): Int =
    when (item) {
        Lower.A -> 1
        Lower.B -> 2
        Lower.C -> 3
        Lower.D -> 4
        Lower.E -> 5
        Lower.F -> 6
        Lower.G -> 7
        Lower.H -> 8
        Lower.I -> 9
        Lower.J -> 10
        Lower.K -> 11
        Lower.L -> 12
        Lower.M -> 13
        Lower.N -> 14
        Lower.O -> 15
        Lower.P -> 16
        Lower.Q -> 17
        Lower.R -> 18
        Lower.S -> 19
        Lower.T -> 20
        Lower.U -> 21
        Lower.V -> 22
        Lower.W -> 23
        Lower.X -> 24
        Lower.Y -> 25
        Lower.Z -> 26
        Upper.A -> 27
        Upper.B -> 28
        Upper.C -> 29
        Upper.D -> 30
        Upper.E -> 31
        Upper.F -> 32
        Upper.G -> 33
        Upper.H -> 34
        Upper.I -> 35
        Upper.J -> 36
        Upper.K -> 37
        Upper.L -> 38
        Upper.M -> 39
        Upper.N -> 40
        Upper.O -> 41
        Upper.P -> 42
        Upper.Q -> 43
        Upper.R -> 44
        Upper.S -> 45
        Upper.T -> 46
        Upper.U -> 47
        Upper.V -> 48
        Upper.W -> 49
        Upper.X -> 50
        Upper.Y -> 51
        Upper.Z -> 52
    }