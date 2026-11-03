package arraysandhashing

class ContainsDuplicateMeu {
    fun containsDuplicate(nums: IntArray): Boolean {
        val tempSet = HashSet<Int>()
        for (i in 0..nums.lastIndex) {
            tempSet.add(nums[i])
            if(tempSet.size <= i) {
                return true
            }
        }
        return false
    }
}

class ContainsDuplicateMelhorado {
    fun containsDuplicate(nums: IntArray): Boolean {
        val tempSet = HashSet<Int>()
        for (num in nums) {
            if(!tempSet.add(num)) return true
        }
        return false
    }
}

// Não ficou tão rápida, provavelmente por conta do early return que a gente faz nas outras
class ContainsDuplicateToSet {
    fun containsDuplicate(nums: IntArray): Boolean {
        if(nums.toSet().size != nums.size) return true
        return false
    }
}

fun main() {
    val solver = ContainsDuplicateMelhorado()
    println(solver.containsDuplicate(intArrayOf(1,2,3,4)))
    println(solver.containsDuplicate(intArrayOf(1,2,3,4,1)))
    println(solver.containsDuplicate(intArrayOf(1,1,1,3,3,4,3,2,4,2)))
}
