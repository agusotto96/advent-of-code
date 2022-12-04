package y2022

import java.io.File

typealias ElfSections = IntRange

typealias ElfSectionsPair = Pair<ElfSections, ElfSections>

fun elfSectionsPairs(file: File): List<ElfSectionsPair> =
    file.readLines()
        .flatMap { it.split(",") }
        .map { it.split("-") }
        .map { it.first().toInt()..it.last().toInt() }
        .chunked(2)
        .map { it.first() to it.last() }

fun fullOverlap(pair: ElfSectionsPair): Boolean =
    pair.first.all { pair.second.contains(it) } || pair.second.all { pair.first.contains(it) }

fun overlap(pair: ElfSectionsPair): Boolean =
    pair.first.any { pair.second.contains(it) }

fun fullOverlapCount(pairs: List<ElfSectionsPair>): Int =
    pairs.count(::fullOverlap)

fun overlapCount(pairs: List<ElfSectionsPair>): Int =
    pairs.count(::overlap)