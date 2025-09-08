package com.example.simplegrocerylist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDAO(): ItemDAO

    companion object {
        @Volatile
        private var INSTANCE: ItemDatabase? = null

        fun getDataBase(
            context: Context,
            scope: CoroutineScope
        ): ItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(ItemDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                instance

            }

        }

        fun populateDatabase(itemDao: ItemDAO) {
            itemDao.insert(Item("Хлеб", "2 шт", 1,false))
            itemDao.insert(Item("Булочки", "4 шт", 1,false))
            itemDao.insert(Item("Пончики", "2 шт", 1,false))
            itemDao.insert(Item("Макароны", "2 шт", 2,false))
            itemDao.insert(Item("Рис", "1 шт", 2,false))
            itemDao.insert(Item("Гречка", "1 шт", 2,false))
            itemDao.insert(Item("Кефир", " 1 л", 3,false))
            itemDao.insert(Item("Молоко", "2 л", 3,false))
        }

        private class ItemDatabaseCallback(
            private val scope: CoroutineScope
        ) : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.itemDAO())
                    }
                }
            }


        }

    }
}