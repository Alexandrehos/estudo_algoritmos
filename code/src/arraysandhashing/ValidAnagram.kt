package arraysandhashing

fun charCount(s: String): Map<String, Int> {
    var count = mutableMapOf<String, Int>()
    for (char in s) {
        count[char] += 1
    }
}

class ValidAnagram {
    fun isAnagram(s: String, t: String): Boolean {
        val stringSArray = s.toCharArray()
        val stringTArray = t.toCharArray()
        for(char in stringSArray) {
            println(char)
        }
        return true
    }
}

fun main() {
    val solver = ValidAnagram()
    println(solver.isAnagram("anagram", "nagaram"))
    println(solver.isAnagram("rat", "cat"))
}
