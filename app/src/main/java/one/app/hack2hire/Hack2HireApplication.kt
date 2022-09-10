package one.app.hack2hire

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import one.app.hack2hire.repository.BookingRepository
import one.app.hack2hire.source.remote.RemoteDataSource

class Hack2HireApplication: Application() {
    companion object{
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataStore")
        val bookingRepository: BookingRepository by lazy { BookingRepository(RemoteDataSource()) }
    }
}
