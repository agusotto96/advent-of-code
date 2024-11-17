package y2021

import y2021.Command.Down
import y2021.Command.Forward
import y2021.Command.Up
import java.io.File

sealed class Command(val units: Int) {
    class Forward(units: Int) : Command(units)
    class Down(units: Int) : Command(units)
    class Up(units: Int) : Command(units)
}

data class State(
    val horizontalPosition: Int = 0,
    val depth: Int = 0,
    val aim: Int = 0,
)

fun commands(file: File): List<Command> =
    file.readLines()
        .map { it.split(' ') }
        .map { (commandStr, unitsStr) ->
            val units = unitsStr.toInt()
            when (commandStr) {
                "forward" -> Forward(units)
                "down" -> Down(units)
                "up" -> Up(units)
                else -> throw IllegalArgumentException("Unknown command: $commandStr")
            }
        }

fun course(state: State, commands: List<Command>, action: (State, Command) -> State): Int =
    commands
        .fold(state, action)
        .let { it.horizontalPosition * it.depth }

fun simpleAction(state: State, command: Command): State =
    when (command) {
        is Forward -> state.copy(horizontalPosition = state.horizontalPosition + command.units)
        is Down -> state.copy(depth = state.depth + command.units)
        is Up -> state.copy(depth = state.depth - command.units)
    }

fun complexAction(state: State, command: Command): State =
    when (command) {
        is Forward -> state.copy(
            horizontalPosition = state.horizontalPosition + command.units,
            depth = state.depth + state.aim * command.units,
        )
        is Down -> state.copy(aim = state.aim + command.units)
        is Up -> state.copy(aim = state.aim - command.units)
    }
