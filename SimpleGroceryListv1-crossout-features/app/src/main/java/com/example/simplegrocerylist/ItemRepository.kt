package com.example.simplegrocerylist

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope

class ItemRepository(context : Context, scope: CoroutineScope) {
    private val itemDAO : ItemDAO
    val allItems: LiveData<List<Item>>

    init {
        val database: ItemDatabase = ItemDatabase.getDataBase(context,scope)
        itemDAO = database.itemDAO()
        allItems = itemDAO.allItems()

    }

    fun insert(item: Item){
        itemDAO.insert(item)
    }

    fun update(item:Item){
        itemDAO.update(item)
    }
    fun delete(item:Item){
        itemDAO.delete(item)
    }

}