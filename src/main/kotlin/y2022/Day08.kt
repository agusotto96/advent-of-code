package y2022

import java.io.File

fun treeGrid(file: File): List<List<Int>> =
    file.readLines().map { it.map(Char::digitToInt) }

fun countVisibleTrees(treeGrid: List<List<Int>>): Int {
    var count = (treeGrid.size - 1) * 4
    val innerTreeRange = 1 until treeGrid.size - 1
    for (i in innerTreeRange) {
        for (j in innerTreeRange) {
            val tree = treeGrid[i][j]
            val (upTrees, downTrees, leftTrees, rightTrees) = directTrees(i, j, treeGrid)
            if (upTrees.all { it < tree }
                || downTrees.all { it < tree }
                || leftTrees.all { it < tree }
                || rightTrees.all { it < tree }) {
                count++
            }
        }
    }
    return count
}

fun maxScenicScore(treeGrid: List<List<Int>>): Int {
    var maxScore = 0
    val treeRange = treeGrid.indices
    for (i in treeRange) {
        for (j in treeRange) {
            val tree = treeGrid[i][j]
            val (upTrees, downTrees, leftTrees, rightTrees) = directTrees(i, j, treeGrid)
            val upViewingDistance = viewingDistance(tree, upTrees)
            val downViewingDistance = viewingDistance(tree, downTrees)
            val leftViewingDistance = viewingDistance(tree, leftTrees)
            val rightViewingDistance = viewingDistance(tree, rightTrees)
            val scenicScore = upViewingDistance * downViewingDistance * leftViewingDistance * rightViewingDistance
            if (scenicScore > maxScore) {
                maxScore = scenicScore
            }
        }
    }
    return maxScore
}

fun directTrees(i: Int, j: Int, treeGrid: List<List<Int>>): List<List<Int>> {
    val upTrees = (0 until i).map { treeGrid[it][j] }.asReversed()
    val downTrees = (i + 1 until treeGrid.size).map { treeGrid[it][j] }
    val leftTrees = treeGrid[i].subList(0, j).asReversed()
    val rightTrees = treeGrid[i].subList(j + 1, treeGrid.size)
    return listOf(upTrees, downTrees, leftTrees, rightTrees)
}

fun viewingDistance(tree: Int, directTrees: List<Int>): Int {
    var count = 0
    for (directTree in directTrees) {
        count++
        if (tree <= directTree) {
            return count
        }
    }
    return count
}
