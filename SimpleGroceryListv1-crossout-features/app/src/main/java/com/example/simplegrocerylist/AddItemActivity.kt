package com.example.simplegrocerylist

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.simplegrocerylist.Item
import com.example.simplegrocerylist.R


class AddItemActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var amountEditText:EditText
    private lateinit var titleHint :TextView
    private lateinit var amountHint :TextView



    private lateinit var btn_category_bread: Button
    private lateinit var btn_category_milk: Button
    private lateinit var btn_category_bakaleya: Button
    private lateinit var btn_category_meat: Button
    private lateinit var btn_category_kolbasa: Button
    private lateinit var btn_category_vegetables: Button
    private lateinit var btn_category_fish: Button
    private lateinit var btn_category_sweets: Button
    private lateinit var btn_category_drinks: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_item)

        titleEditText = findViewById(R.id.titleEditText)
        amountEditText = findViewById(R.id.amountEditText)

//        titleHint = findViewById(R.id.titleHint)
//        amountHint = findViewById(R.id.amountHint)
//        //Textwatchers
//        amountEditText.addTextChangedListener(MyTextWatcher(amountHint))
//        titleEditText.addTextChangedListener(MyTextWatcher(titleHint))



        btn_category_bread=findViewById(R.id.btn_bread)
        btn_category_milk=findViewById(R.id.btn_milk)
        btn_category_bakaleya=findViewById(R.id.btn_bakaleya)
        btn_category_meat=findViewById(R.id.btn_meat)
        btn_category_kolbasa=findViewById(R.id.btn_kolbasa)
        btn_category_vegetables=findViewById(R.id.btn_vegetables)
        btn_category_fish=findViewById(R.id.btn_fish)
        btn_category_sweets=findViewById(R.id.btn_sweet)
        btn_category_drinks=findViewById(R.id.btn_drinks)




        supportActionBar?.setTitle("Simple Grocery List")
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        val item = intent.getSerializableExtra(ITEM_EXTRA_KEY) as? Item
        if(item !=null ){
            supportActionBar?.setTitle("Edit item")
            titleEditText.setText(item.title)
            amountEditText.setText(item.amount)
            category = item.category
            crossout = item.crossout


        }else {
            supportActionBar?.setTitle("Add item")
        }

        btn_category_bread.setOnClickListener { category = 1 }
        btn_category_bakaleya.setOnClickListener{ category = 2 }
        btn_category_milk.setOnClickListener{ category = 3 }
        btn_category_meat.setOnClickListener{ category = 4 }
        btn_category_kolbasa.setOnClickListener{ category = 5 }
        btn_category_vegetables.setOnClickListener{ category = 6 }
        btn_category_fish.setOnClickListener{ category = 7 }
        btn_category_sweets.setOnClickListener{ category = 8 }
        btn_category_drinks.setOnClickListener { category = 9 }


    }





    private  fun  saveItem() {
        val title = titleEditText.text.toString()
        val amount = amountEditText.text.toString()
        if(title.isBlank() || amount.isBlank()|| category==0){
            Toast.makeText(this,"Please insert a title and description",Toast.LENGTH_SHORT).show()
            return
        }
        val result = Item(title,amount, category,crossout)
        val id = (intent.getSerializableExtra(ITEM_EXTRA_KEY) as? Item)?.id
        if (id !=null) {
            result.id = id
        }
        val data = Intent()
        data.putExtra(ITEM_EXTRA_KEY,result)
        setResult(Activity.RESULT_OK, data)
        finish()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()){
            R.id.save_item -> {
                saveItem()
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }
    companion object {
        const val ITEM_EXTRA_KEY="key 1234"
        var category = 0
        var crossout = false
    }
}
