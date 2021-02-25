package com.simple.pos.shared

import android.app.Application
import com.simple.pos.base.util.UtilProvider
import com.simple.pos.shared.util.StoreUtil
import com.simple.pos.shared.util.TokenUtil
import com.simple.pos.shared.util.UserUtil

class SimplePosApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        UtilProvider.initialize(this,
            TokenUtil::class.java,
            UserUtil::class.java,
            StoreUtil::class.java
        )
    }
}