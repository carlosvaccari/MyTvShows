package com.cvaccari.commons.extensions

import androidx.core.text.HtmlCompat

fun String?.fromHtml(): String {
    return HtmlCompat.fromHtml(
        this.orEmpty(),
        HtmlCompat.FROM_HTML_MODE_LEGACY
    ).toString()
}

