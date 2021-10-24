@file:JvmName("HttpRequestSet")

package com.github.unscientificjszhai.simpleupdatehelper.core

import com.github.unscientificjszhai.simpleupdatehelper.core.data.Release
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

fun OkHttpClient.getNewestRelease(owner: String, repoName: String, accept: String): Release? {
    val request = Request.Builder()
        .url("https://api.github.com/repos/$owner/$repoName/releases/latest")
        .header("accept", accept)
        .build()
    val call = this.newCall(request)
    val response = try {
        call.execute()
    } catch (e: IOException) {
        return null
    }

    val rootJsonObject = JSONObject(response?.body()?.string() ?: return null)
    return parseRelease(rootJsonObject)
}