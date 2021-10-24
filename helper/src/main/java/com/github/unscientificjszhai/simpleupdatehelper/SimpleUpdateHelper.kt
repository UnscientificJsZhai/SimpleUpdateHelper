package com.github.unscientificjszhai.simpleupdatehelper

import androidx.annotation.WorkerThread
import com.github.unscientificjszhai.simpleupdatehelper.core.comparator.DefaultTagComparator
import com.github.unscientificjszhai.simpleupdatehelper.core.comparator.TagComparator
import com.github.unscientificjszhai.simpleupdatehelper.core.data.Release
import com.github.unscientificjszhai.simpleupdatehelper.core.getNewestRelease
import okhttp3.OkHttpClient

/**
 * 简易更新检查器。其主要方法必须在工作线程中运行。
 *
 * @author UnscientificJsZhai
 * @constructor 自定义版本对比器的构造方法。
 * @param owner 仓库拥有者。
 * @param repoName 项目仓库名。
 * @param comparator 对比器。
 */
class SimpleUpdateHelper(
    private val owner: String,
    private val repoName: String,
    private val comparator: TagComparator
) {

    /**
     * 默认构造方法，使用默认版本对比器。
     *
     * @param owner 仓库拥有者。
     * @param repoName 项目仓库名。
     * @see DefaultTagComparator
     */
    constructor(owner: String, repoName: String) : this(
        owner,
        repoName,
        TagComparator.getInstance()
    )

    /**
     * 用于处理网络请求的OkHttp客户端。
     */
    private val client = OkHttpClient()

    /**
     * 获取最新的Release。
     *
     * @return 最新的Release对象。如果请求过程发生异常，则返回null。
     */
    @WorkerThread
    @Suppress("unused")
    fun getNewestRelease() = client.getNewestRelease(owner = this.owner, repoName = this.repoName)

    /**
     * 获取比当前版本新的Release。会通过传入的版本比较器进行比较判断目前GitHub仓库的最新Release是否比当前版本更新。
     *
     * @return 最新的Release对象。如果请求过程发生异常，则抛出异常。如果没有更新版本，则返回null。
     */
    @WorkerThread
    @Suppress("unused")
    @Throws(Exception::class)
    fun getNewerRelease(currentVersion: String): Release? {
        val release =
            client.getNewestRelease(owner, repoName) ?: throw RuntimeException("Null Response")

        return if (comparator.isNewer(currentVersion, release.tagName)) {
            release
        } else {
            null
        }
    }
}