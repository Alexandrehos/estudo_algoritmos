package arraysandhashing

import jdk.internal.org.jline.utils.Colors.s

fun charCount(s: String): Map<Char, Int> {
    val count = mutableMapOf<Char, Int>()
    for (char in s) {
        count[char] = (count[char] ?: 0) + 1
    }
    return count
}

class ValidAnagram {
    fun isAnagramMeu(s: String, t: String): Boolean {
        if(s.length != t.length) return false
        val stringSArray = charCount(s)
        val stringTArray = charCount(t)
        return stringSArray == stringTArray
    }

    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val count = IntArray(26)
        for (i in s.indices) {
            count[s[i] - 'a']++
            count[t[i] - 'a']--
        }

        for (value in count) {
            if (value != 0) {
                return false
            }
        }
        return true
    }
}

fun main() {
    val solver = ValidAnagram()
    println(solver.isAnagram("anagram", "nagaram"))
    println(solver.isAnagram("rat", "cat"))
}
