package com.simple.pos.shared.util

import android.content.Context
import com.google.gson.Gson
import com.simple.pos.base.util.UtilInterface
import com.simple.pos.shared.model.submodel.ExtraPay

class ExtraPayUtil : SharedPreferencesUtil<ExtraPay?> {

    companion object {
        private const val SHARED_PREFS_NAME = "ExtraPaySharedPrefs"
        private const val SESSION_TOKEN = "SessionToken"
    }

    constructor() {}

    constructor(context: Context?, SharedPrefsName: String?) : super(context, SharedPrefsName) {}

    public override fun getSessionData(): ExtraPay? {
        val sessionDataJson = sharedPrefs.getString(SESSION_TOKEN, null)
        return if (sessionDataJson != null) {
            Gson().fromJson(sessionDataJson, ExtraPay::class.java)
        } else null
    }

    override fun setSessionData(sessionData: ExtraPay?) {
        val editor = sharedPrefs.edit()
        editor.putString(SESSION_TOKEN, Gson().toJson(sessionData))
        editor.apply()
    }

    override fun initialize(sessionData: ExtraPay?): ExtraPay? {
        return super.initialize(sessionData)!!
    }

    override fun destroy() {
        super.destroy()
    }

    override fun update(sessionData: ExtraPay?) {
        super.update(sessionData)
    }

    override fun create(context: Context): UtilInterface {
        return ExtraPayUtil(context, SHARED_PREFS_NAME)
    }
}