fun main() {
//    dogovorCount (n)  - Кол-во договоров, которое надо разнести (всего сотрудников) 2 ≤ n
//    timeOut      (t)  - через это время уйдет нужный сотрудник                      t ≤ 100
//    etagi             - номера этажей на которых находятся сотрудники
//    goOutClientNumber - клиент который скоро уйдет через timeOut минут и ему надо успеть отнести
    val (dogovorCount, timeOut) = readLine()!!.split(" ").map(String::toInt)
    val etagi = readLine()!!.split(" ").map(String::toInt)
    val goOutClientNumber = readLine()!!.toInt()

    if (dogovorCount != etagi.size || dogovorCount < 2 || timeOut > 100 || timeOut < 1) {
        throw IllegalArgumentException()
    }

    print(calc(timeOut, goOutClientNumber, etagi))
}

fun calc(timeOut: Int, goOutClientNumber: Int, etagi: List<Int>): Int {
    val maxEtag = etagi.last()
    val minEtag = etagi.first()
    val clientEtag = etagi[goOutClientNumber - 1]

    if ((clientEtag - minEtag) <= timeOut || (maxEtag - clientEtag) <= timeOut) {
        return maxEtag - minEtag
    }

    return if (maxEtag - clientEtag > clientEtag - minEtag) {
        (clientEtag - minEtag) + (maxEtag - minEtag)
    } else {
        (maxEtag - clientEtag) + (maxEtag - minEtag)
    }
}