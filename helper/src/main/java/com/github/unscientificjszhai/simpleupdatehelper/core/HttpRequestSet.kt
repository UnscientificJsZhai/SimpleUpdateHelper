@file:JvmName("HttpRequestSet")

package com.github.unscientificjszhai.simpleupdatehelper.core

import com.github.unscientificjszhai.simpleupdatehelper.core.data.Release
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

/**
 * GitHub推荐的接受类型。
 */
internal const val ACCEPT = "application/vnd.github.v3+json"

/**
 * 获取最新的Release。
 *
 * @param owner GitHub仓库所有者。
 * @param repoName GitHub仓库名。
 * @return 最新Release对象。
 */
internal fun OkHttpClient.getNewestRelease(
    owner: String,
    repoName: String
): Release? {
    val request = Request.Builder()
        .url("https://api.github.com/repos/$owner/$repoName/releases/latest")
        .header("accept", ACCEPT)
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