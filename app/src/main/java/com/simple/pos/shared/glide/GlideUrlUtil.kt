package com.simple.pos.shared.glide

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.simple.pos.base.util.UtilProvider
import com.simple.pos.shared.util.TokenUtil

object GlideUrlUtil {
    fun convertToAuthorizedUrl(url: String): GlideUrl {
        val token = (UtilProvider.getUtil(TokenUtil::class.java) as TokenUtil).sessionData
        val header = LazyHeaders.Builder().addHeader("Authorization", "Bearer " + token.token).build()

        return GlideUrl(url, header)
    }
}