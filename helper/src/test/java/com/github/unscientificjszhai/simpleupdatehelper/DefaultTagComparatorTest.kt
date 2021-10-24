package com.github.unscientificjszhai.simpleupdatehelper

import com.github.unscientificjszhai.simpleupdatehelper.core.comparator.TagComparator
import org.junit.Test

/**
 * 测试自带Tag对比器。
 */
class DefaultTagComparatorTest {

    @Test
    fun compareTest() {
        val comparator: TagComparator = TagComparator.getInstance()

        assert(comparator.isNewer("1.0.0", "1.1.0"))
        assert(!comparator.isNewer("1.1.0", "1.0.0"))
        assert(comparator.isNewer("1.0.0-alpha01", "1.1.0"))
        assert(!comparator.isNewer("1.1.0", "1.0.0-alpha01"))
        assert(comparator.isNewer("1.2", "1.2.0"))
        assert(!comparator.isNewer("1.2.0", "1.2"))

        assert(!comparator.isNewer("1.1", "1.1"))
        assert(!comparator.isNewer("1.1-alpha01", "1.1"))
    }
}