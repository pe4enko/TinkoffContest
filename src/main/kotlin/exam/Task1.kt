package exam

/**
 * Андрей и Слава оценивают смешные картинки в интернете.
 */
fun main() {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)
    check(n in 1..100 && m in 1..100) { "Wrong parameters" }

    print(getMaxBaseCollage(n, m))
}

fun getMaxBaseCollage(n: Int, m: Int): String = buildString {
    val diff = n - m

    if (diff == 0) {
        append("XY".repeat(n))
    } else {
        if (diff > 0) {
            append("XY".repeat(m))
            append("X".repeat(diff))
        } else {
            append("XY".repeat(n))
            append("Y".repeat(-diff))
        }
    }
}