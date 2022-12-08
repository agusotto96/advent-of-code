package y2022

// Find all the directories with a total size of at most 100000, then calculate the sum of their total sizes.

typealias Directory = MutableList<String>

fun Directory.str(): String = joinToString(".")

fun List<String>.str2(): String = joinToString(".")

fun asd(outputLines: List<OutputLine>, maxSize: Int): Int {
    val directory: Directory = mutableListOf()
    val directories = mutableSetOf<String>()
    val directoryFilesSize = mutableMapOf<String, Int>()
    val nestedDirectories = mutableMapOf<String, Set<String>>()
    for (outputLine in outputLines) {
        when (outputLine) {
            is OutputLine.ChangeDirectory -> {
                when (val argument = outputLine.argument) {
                    is ChangeDirectoryArgument.Directory -> {
                        directory.add(argument.directoryName)
                        directories.add(directory.str())
                    }
                    is ChangeDirectoryArgument.Previous -> directory.removeLast()
                }
            }
            is OutputLine.DataFile -> {
                directoryFilesSize[directory.str()] = (directoryFilesSize[directory.str()] ?: 0) + outputLine.size
            }
            is OutputLine.Directory -> {
                nestedDirectories[directory.str()] = (nestedDirectories[directory.str()] ?: emptySet()) + ((directory + outputLine.name).str2())
            }
            is OutputLine.ListDirectory -> {}
        }
    }
    return directories
        .map { findAllSubdirectories(it, nestedDirectories) + it }
        .map { dirs -> dirs.sumOf { dir -> directoryFilesSize[dir] ?: 0 } }
        .filter { it <= maxSize }
        .sum()
}

fun findAllSubdirectories(directory: String, nestedDirectories: Map<String, Set<String>>): Set<String> {
    val subdirectories = nestedDirectories[directory]
    if (subdirectories != null) {
        for (subdirectory in subdirectories) {
            return subdirectories + findAllSubdirectories(subdirectory, nestedDirectories)
        }
    }
    return emptySet()
}

fun outputLines(file: java.io.File): List<OutputLine> {
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
