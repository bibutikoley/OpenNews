package io.bibuti.opennews.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Defining the App Database.
 */
@Database(entities = [SingleNewsItem::class], version = 1)
abstract class AppDB : RoomDatabase() {

    /**
     * Declare all the DAOs here..
     */
    abstract fun newsDao(): NewsDao

    /**
     * create room database instance
     */
    companion object {

        @Volatile
        private var DB_INSTANCE: AppDB? = null
        private const val DB_NAME = "app_db"

        fun getDatabase(context: Context): AppDB {
            return DB_INSTANCE ?: synchronized(this) {
                val roomInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().build()
                DB_INSTANCE = roomInstance
                roomInstance
            }
        }

    }


}