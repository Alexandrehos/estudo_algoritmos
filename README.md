# 0 - Índice

- [0 - Índice](#0---índice)
- [1 - Roadmap](#1---roadmap)
- [2 - Arrays \& Hashing](#2---arrays--hashing)
  - [2.1 - Contains Duplicate](#21---contains-duplicate)
  - [2.2 - Valid Anagram](#22---valid-anagram)
  - [2.3 - Two Sum](#23---two-sum)

# 1 - Roadmap

<img src="res/roadmap.png" />

# 2 - Arrays & Hashing

## 2.1 - [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/description/)

A gente precisa retornar se um array contem ou não números duplicados. O segredo é usar `set`s. A solução ótima que encontrei:

```kt
class ContainsDuplicate {
    fun containsDuplicate(nums: IntArray): Boolean {
        val tempSet = HashSet<Int>()
        for (num in nums) {
            if(!tempSet.add(num)) return true
        }
        return false
    }
}
```

Na solução documentada ele cita 3 maneiras, o brute force, que seria basicamente usar um for dentro de outro(Basic Array Traversal), uma com sorting, que também parece obvia, a gente usa um método pronto do kotlin para ordenar o array e ai ve se dois elementos seguidos são iguais, mas não adotei por sentir que ordenar iria fugir da proposta do exercício. E a terceita, que é a realizada, usando hash sets, a solução ótima.

---

- Basic Array Traversal
  - Complexidade de tempo: O(n^2)
  - Complexidade de memória: O(1)
- Sorting
  - Complexidade de tempo: O(n log n)
  - Complexidade de memória: O(1) ou O(n) dependendo do método de sorting
- Hash Sets
  - Complexidade de tempo: O(n)
  - Complexidade de memória: O(n)

## 2.2 - [Valid Anagram](https://leetcode.com/problems/valid-anagram/description/)

Temos 2 strings, temos que dizer se uma é um anagrama da outra. Aqui o segredo para uma boa solução é contar a quantidade de caracteres de cada uma, salvar num hash map, e depois comparar os dois, nessa hora o Kotlin ajuda bastante a gente. A parte de separar a string em hash map eu joguei num método. Abaixo a solução:

```kt
fun charCount(s: String): Map<Char, Int> {
    val count = mutableMapOf<Char, Int>()
    for (char in s) {
        count[char] = (count[char] ?: 0) + 1
    }
    return count
}

class ValidAnagram {
    fun isAnagram(s: String, t: String): Boolean {
        if(s.length != t.length) return false
        val stringSArray = charCount(s)
        val stringTArray = charCount(t)
        return stringSArray == stringTArray
    }
}
```

Ele oferece, novamente, 3 soluções. A primeira é a mais obvia, separa a string, sort e compara elas. A solução seguinte é como a nossa, usando um hash map, mas como ele antes garante tamanho igual para as duas strings, ele itera apenas uma vez nela:

```kt
class ValidAnagram {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val countS = mutableMapOf<Char, Int>()
        val countT = mutableMapOf<Char, Int>()
        for (i in s.indices) {
            countS[s[i]] = countS.getOrDefault(s[i], 0) + 1
            countT[t[i]] = countT.getOrDefault(t[i], 0) + 1
        }
        return countS == countT
    }
}
```

Bem inteligente, economiza uns ciclos. Nos meus testes ficou um pouco mais lento, talvez por conta dele usar o método `getOrDefault()` e não o operador Elvis(`?:`).

Por último, ele tem usa um array como hash table. Aqui tem um detalhe bem interessante, no enunciado ele cita que serão sempre letras do alfabeto ingles minusculas. Por conta disso, podemos limitar nosso universo de `char` possíveis a 26. O resto é mais fácil explicar com o código:

```kt
class ValidAnagram {
class Solution {
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
}
```

A gente tem o early return e, ai criamos um array com 26 posições. Depois temos um loop, e dentro dele a parte mais interessante desse código:

```kt
count[s[i] - 'a']++
count[t[i] - 'a']--
```

Aqui ele ta operando com os chars. O que vai acontecer é que o Kotlin vai pegar o valor da tabela asc de cada um e fazer a conta. Como ele tira o valor de "a", se for `"a" - "a"` temos a posição 0 do array, se for `"c" - "a"`, teremos a posição 2, e assim por diante. E ai, no mesmo array ele subtrai o valor. Ou seja, se entrou X a's da primeira string, se não entrarem X a's da segunda string, o valor será 0. E ai a gente fecha o código:

```kt
for (value in count) {
    if (value != 0) {
        return false
    }
}
return true
```

Aqui ele faz exatamente isso. Percorre o array e ve se tem algum valor diferente de 0. Caso positivo, ele sabe que um tem uma letra que o outro não tem.

---

- Sorting
  - Complexidade de tempo: O(n log n + m log m)
  - Complexidade de memória: O(1) ou O(n + m) dependendo do método de sorting
- Hash Map
  - Complexidade de tempo: O(n + m)
  - Complexidade de memória: O(1)
- Hash Table(Com array)
  - Complexidade de tempo: O(n + m)
  - Complexidade de memória: O(1)

## 2.3 - [Two Sum](https://leetcode.com/problems/two-sum/description/)

Nesse exercício a gente recebe um array de números e precisamos retornar o índice de 2 desses números que somadam sejam iguais a um valor alvo. O array pode ter valores repetidos, a gente não pode usar 2 vezes o mesmo valor para chegar a soma, e sempre vai existir apenas uma solução. Abaixo a solução (posteriormente a explicação):

```kt
class TwoSum {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val numIndex = mutableMapOf<Int, Int>()
        for ((i, n) in nums.withIndex()) {
            val difference = target - n
            if(numIndex.containsKey(difference)){
                return intArrayOf(i, numIndex[difference]!!)
            }
            numIndex[n] = i
        }
        return intArrayOf()
    }
}
```

---

Nesse exercício temos algumas soluções interessantes. A solução menos interessante é a de brute force, que só roda o array duas vezes com um loop nestado no outro. Ai temos a primeira, usando um sort:

```kt
class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val A = nums.mapIndexed { index, num -> num to index }.toMutableList()
        A.sortBy { it.first }

        var i = 0
        var j = nums.size - 1
        while (i < j) {
            val cur = A[i].first + A[j].first
            if (cur == target) {
                return intArrayOf(
                    minOf(A[i].second, A[j].second),
                    maxOf(A[i].second, A[j].second)
                )
            } else if (cur < target) {
                i++
            } else {
                j--
            }
        }
        return intArrayOf()
    }
}
```

Ele cria um hash map para armazenar os índices. Depois ele da um sort. E ai a gente tem o pulo do gato, ele pega dois ponteiros, um no começo do array e outro no final, e ai vai caminhando um em direção ao outro, até encontrar a soluçãoi.
