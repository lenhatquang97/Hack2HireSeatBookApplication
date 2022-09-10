package one.app.hack2hire.databases.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import one.app.hack2hire.Hack2HireApplication.Companion.dataStore

object SampleDataStore {
    private val SAMPLE_KEY = stringPreferencesKey("sample_key")
    val getKey: (Context) -> Flow<String> = {
        it.dataStore.data.map { preferences ->
            preferences[SAMPLE_KEY] ?: ""
        }
    }
    suspend fun setKey(context: Context, value: String) {
        context.dataStore.edit { preferences ->
            preferences[SAMPLE_KEY] = value
        }
    }
}
