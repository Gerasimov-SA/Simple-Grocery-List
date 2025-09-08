package com.example.simplegrocerylist

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "goods_table")
class Item(
    val title: String,
    val amount: String,
    val category: Int,
    var crossout: Boolean,

) :Serializable{
    @PrimaryKey(autoGenerate = true)
    var id =0
}