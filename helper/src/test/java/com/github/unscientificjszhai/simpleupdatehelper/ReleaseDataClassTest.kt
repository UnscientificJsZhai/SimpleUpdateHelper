package com.github.unscientificjszhai.simpleupdatehelper

import org.junit.Test

class ReleaseDataClassTest {

    @Test
    fun test(){
        val helper = SimpleUpdateHelper("UnscientificJsZhai", "TimeManager")
        println(helper.getLatestReleaseVersion(""))
        assert(true)
    }
}