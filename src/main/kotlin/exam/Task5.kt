package exam

import kotlin.math.min

/**
 * Вдохновившись исполнителями, получившими популярность анонимно исключительно в интернете,
 * девочка Олеся решила начать музыкальную карьеру и делать песни для геймеров.
 */
fun main() {
    val (H, W, n) = readLine()!!.split(" ").map(String::toInt)
    check(H in 1..1_000_000_000 && W in 1..1_000_000_000 && n in 1..200_000) { "Wrong parameters" }

    val samples = Array(n) { 0 }
    for (i in 0 until W) {
        val wi = readLine()!!.toInt()
        check(wi in 1..1_000_000_000) { "Wi not in range" }
        samples[i] = wi
    }

    minTime(H, W, n, samples).forEach { println(it) }
}

fun minTime(trackCount: Int, tactsInOneTrack: Int, sampleCount: Int, samples: Array<Int>): Array<Int> {
    //Всего заполненных треков может быть либо столько сколько задано треков (если треков < чем сэмплов)
    //Либо заполненных треков может быть ровно столько сколько имеется всего сэмлов (если треков > чем сэмплов)
    val filledTracks = Array(min(trackCount, sampleCount)) { 0 }
    val result = Array(sampleCount) { -1 }

    for ((sampleNumber, tactsInSample) in samples.withIndex()) {
        if (tactsInSample > tactsInOneTrack) {
            continue
        }

        for ((j, trackToFill) in filledTracks.withIndex()) {
            if ((trackToFill + tactsInSample) <= tactsInOneTrack) {
                filledTracks[j] += tactsInSample
                result[sampleNumber] = j + 1
                break
            }
        }
    }

    return result
}