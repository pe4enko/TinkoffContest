package exam

/**
 * Сегодня у девочки Арины из Санкт-Петербурга выпускной, и она хочет,
 * чтобы её первая дискотека прошла идеально.
 * Для этого нужно воспроизвести плейлист в специфическом порядке по определенным правилам.
 */
fun main() {
    val vsegoNaborov = readLine()!!.toInt()
    check(vsegoNaborov in 1..1_000) { "Wrong parameters" }

    val data = ArrayList<List<Int>>(vsegoNaborov)

    for (i in 1..vsegoNaborov) {
        val n = readLine()!!.toInt()
        check(n in 1..1_000) { "Упс, что то пошло не так!!!" }

        val danceabilities = readLine()!!.split(" ").map(String::toInt)
        data.add(danceabilities)
    }

    print(data.joinToString("\n") {
        generateMaxKaifForArina2(it).joinToString(" ") { it.toString() }
    })
}

/**
 * @param danceabilities величины танцевальностей песни по мнению Арины
 * @return песни в порядке проигрывания для получения максимального кайфа
 */
fun generateMaxKaifForArina(danceabilities: List<Int>): List<Int> {
    val permutations = permutate(danceabilities.toMutableList())

    var resultSequence: List<Int> = emptyList()
    var resultNods = mutableListOf<Int>()
    var maxSum = 0;

    for (oneOfPermutation in permutations) {
        val nods = mutableListOf<Int>()
        val tmpList = mutableListOf<Int>()

        oneOfPermutation.forEach {
            tmpList += it
            nods += nod(tmpList)
        }

        val sum = nods.sum()

/*
        if (isNodsAGeNodsB(nods, resultNods)){
            resultSequence = oneOfPermutation
            resultNods = nods
        }
*/

        if (sum >= maxSum) {
            maxSum = sum
            resultSequence = oneOfPermutation
        }
    }
    return resultSequence
}

fun isNodsAGeNodsB(a: List<Int>, b: List<Int>): Boolean {
    if (a.size != b.size) {
        return true
    }
    val aS = a.map { it.toString() }
    val bS = b.map { it.toString() }

    for ((i, ass) in aS.withIndex()) {
        val bss = bS[i]
        if (ass >= bss) {
            continue
        } else {
            return false
        }
    }

    return true
}

fun nod(numbers: List<Int>): Int {
    var leftNumber = numbers[0]
    for (i in 1 until numbers.size) {
        leftNumber = nod(leftNumber, numbers[i])
    }
    return leftNumber
}

fun nod(first: Int, second: Int): Int {
    var a = first
    var b = second

    while (b != 0) {
        val remainder = a % b
        a = b
        b = remainder;
    }
    return a;
}

fun <E> permutate(original: MutableList<E>): List<List<E>> {
    if (original.isEmpty()) {
        return mutableListOf(mutableListOf())
    }

    val firstElement = original.removeAt(0)
    val returnValue = mutableListOf<List<E>>()

    val permutations = permutate(original)

    for (smallerPermutated in permutations) {
        for (index in 0..smallerPermutated.size) {
            val temp = ArrayList(smallerPermutated)
            temp.add(index, firstElement)

            returnValue.add(temp)
        }
    }
    return returnValue
}


/**
 * @param danceabilities величины танцевальностей песни по мнению Арины
 * @return песни в порядке проигрывания для получения максимального кайфа
 */
fun generateMaxKaifForArina2(danceabilities: List<Int>): List<Int> {
    val maxElement = danceabilities.maxOrNull()!!

    val dance = danceabilities.toMutableList()
    dance.remove(maxElement)

    val resultSequence = mutableListOf(maxElement)

    for (i in 0 until dance.size) {
        var maxNod = 0
        var indexToRemove = 0

        for (j in 0 until dance.size) {
            val tmpSet = resultSequence + dance[j]
            val currentNod = nod(tmpSet)

            if (currentNod > maxNod) {
                maxNod = currentNod
                indexToRemove = j
            }
        }
        resultSequence.add(dance.removeAt(indexToRemove))
    }

    return resultSequence
}