package y2019

import java.io.File

/*
An Intcode program is a list of integers separated by commas (like 1,0,0,3,99).
To run one, start by looking at the first integer (called position 0). Here, you will
find an opcode — either 1, 2, or 99. The opcode indicates what to do; for example, 99
means that the program is finished and should immediately halt. Encountering an unknown
opcode means something went wrong.

Opcode 1 adds together numbers read from two positions and stores the result in a
third position. The three integers immediately after the opcode tell you these three
positions—the first two indicate the positions from which you should read the input values,
and the third indicates the position at which the output should be stored.

For example, if your Intcode computer encounters 1,10,20,30, it should read the values
at positions 10 and 20, add those values, and then overwrite the value at position 30
with their sum.

Opcode 2 works exactly like opcode 1, except it multiplies the two inputs instead of
adding them. Again, the three integers after the opcode indicate where the inputs and
outputs are, not their values.

Once you're done processing an opcode, move to the next one by stepping forward 4 positions.
*/

/*
First, you'll need to add two new instructions:

Opcode 3 takes a single integer as input and saves it to the position given by its only parameter.
For example, the instruction 3,50 would take an input value and store it at address 50.

Opcode 4 outputs the value of its only parameter.
For example, the instruction 4,50 would output the value at address 50.

Second, you'll need to add support for parameter modes:

Each parameter of an instruction is handled based on its parameter mode.
Right now, your ship computer already understands parameter mode 0, position mode,
which causes the parameter to be interpreted as a position - if the parameter is 50,
its value is the value stored at address 50 in memory.
Until now, all parameters have been in position mode.

Now, your ship computer will also need to handle parameters in mode 1, immediate mode.
In immediate mode, a parameter is interpreted as a value - if the parameter is 50, its value is simply 50.

Parameter modes are stored in the same value as the instruction's opcode.
The opcode is a two-digit number based only on the ones and tens digit of the value,
that is, the opcode is the rightmost two digits of the first value in an instruction.
Parameter modes are single digits, one per parameter, read right-to-left from the opcode:
the first parameter's mode is in the hundreds digit, the second parameter's mode is in the thousands digit,
the third parameter's mode is in the ten-thousands digit, and so on. Any missing modes are 0.

Parameters that an instruction writes to will never be in immediate mode.

Finally, some notes:

It is important to remember that the instruction pointer should increase by the number of values
in the instruction after the instruction finishes. Because of the new instructions, this amount is no longer always 4.
Integers can be negative: 1101,100,-1,4,0 is a valid program (find 100 + -1, store the result in position 4).

Your computer is only missing a few opcodes:

Opcode 5 is jump-if-true: if the first parameter is non-zero,
it sets the instruction pointer to the value from the second parameter. Otherwise, it does nothing.
Opcode 6 is jump-if-false: if the first parameter is zero,
it sets the instruction pointer to the value from the second parameter. Otherwise, it does nothing.
Opcode 7 is less than: if the first parameter is less than the second parameter,
it stores 1 in the position given by the third parameter. Otherwise, it stores 0.
Opcode 8 is equals: if the first parameter is equal to the second parameter,
it stores 1 in the position given by the third parameter. Otherwise, it stores 0.

Like all instructions, these instructions need to support parameter modes as described above.

Normally, after an instruction is finished, the instruction pointer increases by the number of values in that instruction.
However, if the instruction modifies the instruction pointer, that value is used and the instruction pointer is not
automatically increased.
*/

// OPCODES
const val ADD = 1
const val MULTIPLY = 2
const val INPUT = 3
const val OUTPUT = 4
const val JUMP_IF_TRUE = 5
const val JUMP_IF_FALSE = 6
const val LESS_THAN = 7
const val EQUALS = 8
const val FINISH = 99

// PARAM MODES
const val IMMEDIATE = 1

fun intcodes(file: File): List<Int> =
    file.readLines()
        .flatMap { it.split(",") }
        .map { it.toInt() }

fun run(intcodes: MutableList<Int>, noun: Int? = null, verb: Int? = null, vararg inputs: Int): Int {
    val inputIterator = inputs.iterator()
    if (noun != null) intcodes[1] = noun
    if (verb != null) intcodes[2] = verb
    var output = 0
    var i = 0
    while (i < intcodes.size) {
        val intcode = intcodes[i]
        val firstParamMode = (intcode / 100) % 10
        val secondParamMode = (intcode / 1000) % 10
        val opcode = intcode % 100
        when (opcode) {
            ADD -> {
                val (firstParam, secondParam, thirdParam) = intcodes.subList(i + 1, i + 4)
                val firstValue = if (firstParamMode == IMMEDIATE) firstParam else intcodes[firstParam]
                val secondValue = if (secondParamMode == IMMEDIATE) secondParam else intcodes[secondParam]
                val result = firstValue + secondValue
                intcodes[thirdParam] = result
                i += 4
            }
            MULTIPLY -> {
                val (firstParam, secondParam, thirdParam) = intcodes.subList(i + 1, i + 4)
                val firstValue = if (firstParamMode == IMMEDIATE) firstParam else intcodes[firstParam]
                val secondValue = if (secondParamMode == IMMEDIATE) secondParam else intcodes[secondParam]
                val result = firstValue * secondValue
                intcodes[thirdParam] = result
                i += 4
            }
            INPUT -> {
                val firstParam = intcodes[i + 1]
                intcodes[firstParam] = inputIterator.next()
                i += 2
            }
            OUTPUT -> {
                val firstParam = intcodes[i + 1]
                val firstValue = if (firstParamMode == IMMEDIATE) firstParam else intcodes[firstParam]
                output = firstValue
                i += 2
            }
            JUMP_IF_TRUE -> {
                val (firstParam, secondParam) = intcodes.subList(i + 1, i + 3)
                val firstValue = if (firstParamMode == IMMEDIATE) firstParam else intcodes[firstParam]
                val secondValue = if (secondParamMode == IMMEDIATE) secondParam else intcodes[secondParam]
                if (firstValue != 0) {
                    i = secondValue
                } else {
                    i += 3
                }
            }
            JUMP_IF_FALSE -> {
                val (firstParam, secondParam) = intcodes.subList(i + 1, i + 3)
                val firstValue = if (firstParamMode == IMMEDIATE) firstParam else intcodes[firstParam]
                val secondValue = if (secondParamMode == IMMEDIATE) secondParam else intcodes[secondParam]
                if (firstValue == 0) {
                    i = secondValue
                } else {
                    i += 3
                }
            }
            LESS_THAN -> {
                val (firstParam, secondParam, thirdParam) = intcodes.subList(i + 1, i + 4)
                val firstValue = if (firstParamMode == IMMEDIATE) firstParam else intcodes[firstParam]
                val secondValue = if (secondParamMode == IMMEDIATE) secondParam else intcodes[secondParam]
                val result = if (firstValue < secondValue) 1 else 0
                intcodes[thirdParam] = result
                i += 4
            }
            EQUALS -> {
                val (firstParam, secondParam, thirdParam) = intcodes.subList(i + 1, i + 4)
                val firstValue = if (firstParamMode == IMMEDIATE) firstParam else intcodes[firstParam]
                val secondValue = if (secondParamMode == IMMEDIATE) secondParam else intcodes[secondParam]
                val result = if (firstValue == secondValue) 1 else 0
                intcodes[thirdParam] = result
                i += 4
            }
            FINISH -> break
        }
    }
    return output
}
