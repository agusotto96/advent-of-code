package y2022

import java.io.File

fun directoriesSize(outputLines: List<OutputLine>): Map<List<String>, Int> {
    val directory = mutableListOf<String>()
    val directoriesSize = mutableMapOf<List<String>, Int>()
    for (outputLine in outputLines) {
        when (outputLine) {
            is OutputLine.ChangeDirectory -> {
                when (val argument = outputLine.argument) {
                    is ChangeDirectoryArgument.Directory -> directory.add(argument.directoryName)
                    is ChangeDirectoryArgument.Previous -> directory.removeLast()
                }
            }
            is OutputLine.DataFile -> {
                (1..directory.size)
                    .map(directory::take)
                    .forEach { directoriesSize[it] = (directoriesSize[it] ?: 0) + outputLine.size }
            }
            is OutputLine.Directory -> {}
            is OutputLine.ListDirectory -> {}
        }
    }
    return directoriesSize
}

fun directoriesSizeSum(outputLines: List<OutputLine>, maxSize: Int): Int =
    outputLines
        .let(::directoriesSize)
        .values
        .filter { it <= maxSize }
        .sum()

fun outputLines(file: File): List<OutputLine> {
    val outputLines = mutableListOf<OutputLine>()
    for (line in file.readLines()) {
        if (line.startsWith("$")) {
            val commandLine = line.split(" ", limit = 2).last()
            if (commandLine == "ls") {
                outputLines += OutputLine.ListDirectory
            }
            if (commandLine.startsWith("cd")) {
                val argument = when (val directoryName = commandLine.split(" ").last()) {
                    ".." -> ChangeDirectoryArgument.Previous
                    else -> ChangeDirectoryArgument.Directory(directoryName)
                }
                outputLines += OutputLine.ChangeDirectory(argument)
            }
        } else if (line.startsWith("dir")) {
            val directoryName = line.split(" ").last()
            outputLines += OutputLine.Directory(directoryName)
        } else {
            val (fileSize, fileName) = line.split(" ")
            outputLines += OutputLine.DataFile(fileSize.toInt(), fileName)
        }
    }
    return outputLines
}

sealed interface OutputLine {
    object ListDirectory : OutputLine
    data class ChangeDirectory(val argument: ChangeDirectoryArgument) : OutputLine
    data class DataFile(val size: Int, val name: String) : OutputLine
    data class Directory(val name: String) : OutputLine
}

sealed interface ChangeDirectoryArgument {
    object Previous : ChangeDirectoryArgument
    data class Directory(val directoryName: String) : ChangeDirectoryArgument
}
