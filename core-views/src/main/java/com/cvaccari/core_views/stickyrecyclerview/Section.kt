package com.cvaccari.core_views.stickyrecyclerview

import java.io.Serializable

interface Section : Serializable {

    companion object {
        const val HEADER = 0
        const val ITEM = 1
        const val LOADING = 2
    }

    fun type(): Int

    fun sectionPosition(): Int
}
