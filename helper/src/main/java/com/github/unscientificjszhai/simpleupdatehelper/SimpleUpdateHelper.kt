package com.github.unscientificjszhai.simpleupdatehelper

import androidx.annotation.WorkerThread
import com.github.unscientificjszhai.simpleupdatehelper.core.getNewestRelease
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class SimpleUpdateHelper(val owner: String, val repoName: String) {

    companion object

    @WorkerThread
    fun getLatestReleaseVersion(accept: String): String {
        val client = OkHttpClient()

        return client.getNewestRelease(owner, repoName, accept).toString()
    }
}