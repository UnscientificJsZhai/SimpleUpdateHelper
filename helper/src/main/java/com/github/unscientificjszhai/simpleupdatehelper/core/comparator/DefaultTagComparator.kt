package com.github.unscientificjszhai.simpleupdatehelper.core.comparator

/**
 * 默认的tag对比工具类。
 *
 * 传入的tag参数需要是由点分割的若干位数字，例如：1.0.30。
 *
 * 从前到后，这个字符串会被从所有点分割，并按照顺序比较数字的大小。如果前一位能够区分出大小，则不会考虑后面几位。
 * 例如：1.1.0在比较第一位时就小于2.0.1。
 *
 * 如果两个版本中分割出的片段数量不同，则在对比这一位时直接视为缺失这一位的更小。例如：
 * 1.0小于1.0.1，1.0也小于1.0.0。
 *
 * 对于出现不能解析的字符时，只有可以解析的部分影响判定。如果这条规则影响到了逻辑，建议自己实现[TagComparator]。
 * 例如1.0.1大于2.0.0-alpha01
 *
 * @author UnscientificJsZhai
 */
class DefaultTagComparator : TagComparator {

    companion object {

        /**
         * 默认分割字段数。
         */
        const val DEFAULT_ARRAY_LIST_LENGTH = 3
    }

    override fun isNewer(originalTag: String, newTag: String): Boolean {
        val originalTagArray = originalTag.toIntArray()
        val newTagArray = newTag.toIntArray()
        var index = 0
        while (true) {
            return if (originalTagArray.size <= index) {
                newTagArray.size > index
            } else if (newTagArray.size <= index) {
                false
            } else {
                if (originalTagArray[index] == newTagArray[index]) {
                    index += 1
                    continue
                } else {
                    newTagArray[index] > originalTagArray[index]
                }
            }
        }
    }

    /**
     * 将传入的tag转化为int数组。
     *
     * @return 转化后的数组。
     */
    private fun String.toIntArray(): IntArray {
        val arraylist = ArrayList<Int>(DEFAULT_ARRAY_LIST_LENGTH)
        this.split(".").forEach {
            arraylist.add(it.toIntOrNull() ?: it.startInt() ?: return@forEach)
        }
        return arraylist.toIntArray()
    }

    /**
     * 匹配字符串的前几位的数字为整型并返回。
     *
     * @return 尽可能多的数字位转化为整型数返回。
     */
    private fun String.startInt(): Int? {
        val numberRegex = "[0-9]+".toRegex()
        return numberRegex.find(this)?.value?.toInt()
    }
}