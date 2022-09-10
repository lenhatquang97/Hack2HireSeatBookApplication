package one.app.hack2hire.databases.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import one.app.hack2hire.model.SampleModel

@Database(entities = [SampleModel::class], version = 1, exportSchema = false)
abstract class SampleDatabase: RoomDatabase() {
    abstract fun sampleDao(): SampleDao

    companion object{
        @Volatile
        private var INSTANCE: SampleDatabase? = null

        fun getDatabase(context: Context): SampleDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        SampleDatabase::class.java, "sample_database.db"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

}
