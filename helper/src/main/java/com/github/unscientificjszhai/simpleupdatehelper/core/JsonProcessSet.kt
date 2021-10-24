@file:JvmName("JsonProcessSet")

package com.github.unscientificjszhai.simpleupdatehelper.core

import com.github.unscientificjszhai.simpleupdatehelper.core.data.Asset
import com.github.unscientificjszhai.simpleupdatehelper.core.data.Release
import org.json.JSONException
import org.json.JSONObject

fun parseAsset(assetJsonObject: JSONObject) = try {
    Asset(
        url = assetJsonObject.getString("url"),
        downloadUrl = assetJsonObject.getString("browser_download_url"),
        size = assetJsonObject.getInt("size"),
        uploaderName = assetJsonObject.getJSONObject("uploader").getString("login")
    )
} catch (e: JSONException) {
    null
}


fun parseRelease(releaseJsonObject: JSONObject): Release? {
    val assets = ArrayList<Asset>()
    val assetsJsonArray = releaseJsonObject.getJSONArray("assets")
    for (index in 0 until assetsJsonArray.length()) {
        val assetJsonObject = assetsJsonArray.getJSONObject(index)
        assets.add(
            parseAsset(assetJsonObject) ?: continue
        )
    }

    return try {
        Release(
            url = releaseJsonObject.getString("url"),
            htmlUrl = releaseJsonObject.getString("html_url"),
            id = releaseJsonObject.getInt("id"),
            tagName = releaseJsonObject.getString("tag_name"),
            body = releaseJsonObject.getString("body"),
            draft = releaseJsonObject.getBoolean("draft"),
            prerelease = releaseJsonObject.getBoolean("prerelease"),
            publishedAt = releaseJsonObject.getString("published_at"),
            assets = assets
        )
    } catch (e: JSONException) {
        null
    }
}