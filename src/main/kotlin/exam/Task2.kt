package exam

/**
 * Последние 5 лет Мирон не пользовался интернетом, за это время появились множество криптовалют.
 */
fun main() {
    val (n, k) = readLine()!!.split(" ").map(String::toInt)
    check(n in 1..300_000 && k in 1..300_000) { "Wrong parameters" }

    val security = readLine()!!.map { it == '1' }
    check(security.size == n) { "Wrong security servers array" }
    check(security.first() && security.last()) { "Wrong first and last servers" }

    print(checkSecurityPath(k, security))
}

fun checkSecurityPath(step: Int, security: List<Boolean>): String {
    var nextServerToStartCheckFrom = 0

    do {
        var findSecurityServerInRange = false

        for (i in 1..step) {
            val checkNowServer = nextServerToStartCheckFrom + i
            if (checkNowServer >= security.size) {
                return "NO"
            }

            if (security[checkNowServer]) {
                if (checkNowServer == security.size - 1) {
                    return "YES"
                }
                findSecurityServerInRange = true
                break
            }
        }

        if (!findSecurityServerInRange) {
            return "NO"
        }

        nextServerToStartCheckFrom++
    } while (true)
}

/*
fun checkSecurityPath(step: Int, security: List<Boolean>): String {
    var currentStep = 0
    for (element in security) {
        if (element) {
            currentStep = 0
        } else {
            currentStep++
            if (currentStep > step - 1) {
                return "NO"
            }
        }
    }
    return ("YES")
}*/