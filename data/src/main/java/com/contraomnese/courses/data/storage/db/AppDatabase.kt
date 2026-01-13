package com.contraomnese.courses.data.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.contraomnese.courses.data.storage.db.courses.dao.CoursesDao
import com.contraomnese.courses.data.storage.db.courses.dao.FavoritesDao
import com.contraomnese.courses.data.storage.db.courses.entity.CourseEntity
import com.contraomnese.courses.data.storage.db.courses.entity.FavoriteEntity

private const val DATABASE_VERSION = 1
const val DATABASE_NAME = "contraomnese_courses_db.sqlite3"

@Database(
    entities = [
        CourseEntity::class,
        FavoriteEntity::class,
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao
    abstract fun coursesDao(): CoursesDao

    companion object {
        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}