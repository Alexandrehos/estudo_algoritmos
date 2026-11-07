package arraysandhashing

class TwoSum {
    fun twoSumDuasPassadas(nums: IntArray, target: Int): IntArray {
        val numIndex = mutableMapOf<Int, Int>()
        for (i in nums.indices) numIndex[nums[i]] = i
        for (i in nums.indices) {
            val difference = target - nums[i]
            if(numIndex.containsKey(difference) && numIndex[difference] != i){
                return intArrayOf(i, numIndex[difference]!!)
            }
        }
        return intArrayOf()
    }

    fun twoSumUmaPassada(nums: IntArray, target: Int): IntArray {
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


fun main() {
    val solver = TwoSum()
    println(solver.twoSumUmaPassada(intArrayOf(3, 2, 4), 6).contentToString())
    println(solver.twoSumUmaPassada(intArrayOf(2, 7, 11, 15), 9).contentToString())
    println(solver.twoSumUmaPassada(intArrayOf(3, 3), 6).contentToString())
}
