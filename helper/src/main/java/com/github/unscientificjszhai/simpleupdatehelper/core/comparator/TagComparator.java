package com.github.unscientificjszhai.simpleupdatehelper.core.comparator;

import androidx.annotation.NonNull;

/**
 * 对比两个release的tag的接口。
 * 实现这个接口的{@link #isNewer(String, String)}方法，提供一个对比方法，更新检查器就可以将查询到的release的tag传入，
 * 对比可得两个版本谁是最新版。
 *
 * @author UnscientificJsZhai
 */
public interface TagComparator {

    /**
     * 获取默认实现的比较器。
     *
     * @return 默认比较器，只能对比简单的版本号tag信息。
     * @see DefaultTagComparator
     */
    @NonNull
    static TagComparator getInstance() {
        return new DefaultTagComparator();
    }

    /**
     * 对比两个release的tag，哪个是更新的。返回一个boolean值。
     *
     * @param originalTag 第一个tag。
     * @param newTag      对比目标，第二个tag。
     * @return 如果第二个tag不比第一个更新，包括遇到比较器无法处理的规则，则返回false。如果第二个tag比第一个更新，则返回true。
     */
    boolean isNewer(@NonNull String originalTag, @NonNull String newTag);
}
