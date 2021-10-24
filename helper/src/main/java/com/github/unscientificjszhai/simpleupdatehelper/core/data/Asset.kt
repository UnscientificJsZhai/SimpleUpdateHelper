package com.github.unscientificjszhai.simpleupdatehelper.core.data

/**
 * 单个Asset数据类。
 *
 * @param url 可进一步交互的Url。
 * @param downloadUrl 用于下载的Url。
 * @param size 大小。
 * @param uploaderName 上传者信息。
 */
data class Asset(
    val url: String,
    val downloadUrl: String,
    val size: Int,
    val uploaderName: String
)
