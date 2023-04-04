package com.showcase.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.showcase.AppContext
import com.showcase.AppModule
import com.showcase.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
object DatabaseModule {
    @Provides
    fun provideSqlDriver(@AppContext context: Context): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context)
    }

    @Provides
    @Singleton
    fun provideDatabase(sqlDriver: SqlDriver): Database {
        return Database(sqlDriver)
    }
}