package exam

/**
 * Лиза - первокурсница из подмосковного города Люберцы, и сегодня вместо математики она
 * решила пойти на концерт своего любимого исполнителя! Однако очередь растянулась на весь Арбат,
 * и Лиза испугалась, что если люди будут стоять слишком долго, концерт могут отменить.
 * Поэтому она решила схитрить: организаторы могут продать до трех билетов в одни руки,
 * причем продажа нескольких билетов одному человеку может занимать меньше времени,
 * чем каждому из них по отдельности.
 */
fun main() {
    val tickets = readLine()!!.toInt()
    check(tickets in 1..5000) { "Очень много билетов" }

    val a = Array(tickets + 1) { 0 }
    val b = Array(tickets + 1) { 0 }
    val c = Array(tickets + 1) { 0 }

    for (i in 1..tickets) {
        val times = readLine()!!.split(" ").map(String::toInt)
        a[i] = times[0]
        b[i] = times[1]
        c[i] = times[2]
    }

    print(minTime(a, b, c))
}

fun minTime(a: Array<Int>, b: Array<Int>, c: Array<Int>): Int {
    val t = Array(a.size) { 0 }

    t[0] = 0
    t[1] = a[1]
    t[2] = minOf(a[1] + a[2], b[1])

    for (i in 3 until a.size) {
        t[i] = minOf(
            t[i - 1] + a[i],
            t[i - 2] + b[i - 1],
            t[i - 3] + c[i - 2]
        )
    }

    return t.last()
}
