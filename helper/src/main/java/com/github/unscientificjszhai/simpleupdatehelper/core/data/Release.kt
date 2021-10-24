package com.github.unscientificjszhai.simpleupdatehelper.core.data

/**
 * 单个Release数据类。
 *
 * @param url 用于进一步与GitHub交互的Url。
 * @param htmlUrl 当前release的可浏览页面。
 * @param id ID。
 * @param tagName release的标签名。
 * @param body release的描述。
 * @param draft 是否为草稿。
 * @param prerelease 是否为预发行版。
 * @param publishedAt 发布时间。
 * @param assets release包含的内容。
 */
data class Release(
    val url: String,
    val htmlUrl: String,
    val id: Int,
    val tagName: String,
    val body: String,
    val draft: Boolean,
    val prerelease: Boolean,
    val publishedAt: String,
    val assets: List<Asset>
)