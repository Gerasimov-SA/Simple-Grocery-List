package com.example.simplegrocerylist

import android.annotation.SuppressLint
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher

import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.ItemAdapter
import com.example.simplegrocerylist.AddItemActivity
import com.example.simplegrocerylist.Item
import com.example.simplegrocerylist.ItemViewModel
import com.example.simplegrocerylist.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable


class MainActivity : AppCompatActivity() {
    private val itemViewModel: ItemViewModel by viewModels()
    private var launcher: ActivityResultLauncher<Intent>? = null
    private var launcher2: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val adapter = ItemAdapter()
        recyclerView.adapter = adapter
        //Нажатие на кнопку "Добавить"
        val buttonAddItem: FloatingActionButton = findViewById(R.id.fab_add)
        buttonAddItem.setOnClickListener {
            launcher?.launch(Intent(this@MainActivity, AddItemActivity::class.java))

        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                    if (direction == ItemTouchHelper.LEFT) {
                itemViewModel.delete(adapter.getItemAt(viewHolder.bindingAdapterPosition))
                Toast.makeText(this@MainActivity, "item deleted", Toast.LENGTH_SHORT).show()}
                if (direction == ItemTouchHelper.RIGHT) {
                    //crossout feature
                   val item = adapter.getItemAt(viewHolder.bindingAdapterPosition)
                    if(item.crossout==false){
                    item.crossout = true
                    itemViewModel.update(item)
                    Toast.makeText(this@MainActivity, "item crossouted", Toast.LENGTH_SHORT).show()}
                    else {
                        item.crossout = false
                        itemViewModel.update(item)

                        Toast.makeText(this@MainActivity, "item back to list", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }).attachToRecyclerView(recyclerView)
        //Нажатие на элемент списка чтобы "Редактировать"
        adapter.setOnCLickListener(object : ItemAdapter.OnItemClickListener {
            override fun onItemClick(item: Item?) {
                if (item != null) {
                    launcher2?.launch(Intent(this@MainActivity,AddItemActivity::class.java)
                        .putExtra(AddItemActivity.ITEM_EXTRA_KEY, item))

                }
            }
        })
        itemViewModel.allItems.observe(
            this, object : Observer<List<Item>> {
                override fun onChanged(items: List<Item>) {
                    adapter.setItems(items)
                }
            }
        )

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val item =
                    result.data?.getSerializableExtra(AddItemActivity.ITEM_EXTRA_KEY) as Item
                itemViewModel.insert(item)
            } else {
                Toast.makeText(this, "Item not saved", Toast.LENGTH_SHORT).show()
            }

        }
        launcher2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val item =
                    result.data?.getSerializableExtra(AddItemActivity.ITEM_EXTRA_KEY) as Item
                itemViewModel.update(item)
            } else {
                Toast.makeText(this, "Item not saved", Toast.LENGTH_SHORT).show()
            }

        }
    }

}