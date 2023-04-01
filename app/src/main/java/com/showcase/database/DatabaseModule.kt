package com.showcase.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.showcase.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {
    @Provides
    fun provideSqlDriver(context: Context): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context)
    }

    @Singleton
    @Provides
    fun provideDatabase(sqlDriver: SqlDriver): Database {
        return Database(sqlDriver)
    }
}