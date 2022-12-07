package y2022

import java.io.File

typealias Signal = Sequence<Char>

enum class Marker(val size: Int) { StartOfPacket(4), StartOfMessage(14) }

fun signal(file: File): Signal = file.readText().asSequence()

fun countProcessedSignal(signal: Signal, marker: Marker): Int {
    var count = 0
    for (signalFrame in signal.windowed(marker.size)) {
        if (signalFrame.distinct().count() == marker.size) {
            return count + marker.size
        }
        count++
    }
    return count
}
