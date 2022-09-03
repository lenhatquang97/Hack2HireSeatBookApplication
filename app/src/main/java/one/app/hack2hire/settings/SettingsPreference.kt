package one.app.hack2hire.settings

import android.content.Context

/**
 * Created by lengocanh on 29/08/2022.
 * Copyright (c) {2022} VinID. All rights reserved.
 */
class SettingsPreference(context: Context) {

    companion object {
        private const val DEFAULT_ENDPOINT = "https://api.hack2hire.onemount.dev"
        private const val KEY_ENDPOINT = "endpoint"
    }

    private val pref = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun getEndpoint(): String {
        return pref.getString(KEY_ENDPOINT, null) ?: DEFAULT_ENDPOINT
    }

    fun setEndpoint(endpoint: String) {
        pref.edit().putString(KEY_ENDPOINT, endpoint).apply()
    }
}
