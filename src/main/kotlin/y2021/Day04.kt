package y2021

import java.io.File

typealias Board = List<Set<Int>>

data class Bingo(val numbers: List<Int>, val boards: List<Board>)

fun Bingo(file: File, boardSize: Int): Bingo {
    val lines = file.readLines()
    val numbers = lines.first().split(",").map(String::toInt)
    val boards = lines.asSequence().drop(1).filter(String::isNotBlank)
        .map { it.split(" ").filter(String::isNotBlank).map(String::toInt) }
        .chunked(boardSize).map(::board).toList()
    return Bingo(numbers, boards)
}

fun board(numbers: List<List<Int>>): Board {
    val board = mutableListOf<MutableSet<Int>>()
    for (i in numbers.indices) {
        val row = mutableSetOf<Int>()
        val column = mutableSetOf<Int>()
        for (j in numbers.indices) {
            row.add(numbers[i][j])
            column.add(numbers[j][i])
        }
        board.add(row)
        board.add(column)
    }
    return board
}

fun findFirstWinnerScore(bingo: Bingo): Int? {
    val drawn = mutableSetOf<Int>()
    for (number in bingo.numbers) {
        drawn.add(number)
        for (board in bingo.boards) {
            for (group in board) {
                if (drawn.containsAll(group)) {
                    return score(number, drawn, board)
                }
            }
        }
    }
    return null
}

fun findLastWinnerScore(bingo: Bingo): Int? {
    val drawn = mutableSetOf<Int>()
    val scoresByWinner = mutableMapOf<Board, Int>()
    var lastWinner: Board? = null
    for (number in bingo.numbers) {
        drawn.add(number)
        for (board in bingo.boards) {
            for (group in board) {
                if (drawn.containsAll(group) && !scoresByWinner.contains(board)) {
                    scoresByWinner[board] = score(number, drawn, board)
                    lastWinner = board
                }
            }
        }
    }
    return scoresByWinner[lastWinner]
}

fun score(number: Int, numbers: Set<Int>, board: Board): Int {
    return number * (board.flatten().toSet() - numbers).sum()
}