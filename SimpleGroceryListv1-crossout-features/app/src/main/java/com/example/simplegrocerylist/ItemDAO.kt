package com.example.simplegrocerylist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.lifecycle.LiveData

@Dao
interface ItemDAO {

    @Insert
    fun insert(item : Item)
    @Update
    fun update(item: Item)
    @Delete
    fun delete(item: Item)

    @Query("SELECT*FROM goods_table ORDER BY category DESC")
    fun allItems():LiveData<List<Item>>

}