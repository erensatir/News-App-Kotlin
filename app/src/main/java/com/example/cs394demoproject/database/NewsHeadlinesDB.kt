package com.example.cs394demoproject.database

import android.content.Context
import androidx.room.*
import com.example.cs394demoproject.model.News

@Database(
        entities = [News::class],
        version = 1,
    exportSchema = false
)
@TypeConverters(Convertors::class)
abstract class NewsHeadlinesDB : RoomDatabase() {

    abstract fun getNewsDao(): NewsHeadlinesDAO

    companion object {
        @Volatile
        private var instance: NewsHeadlinesDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        NewsHeadlinesDB::class.java,
                    "article_db.db"
                ).build()
    }
}

