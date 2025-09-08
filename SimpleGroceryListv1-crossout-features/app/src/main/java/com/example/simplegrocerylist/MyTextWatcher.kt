package com.example.simplegrocerylist

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

class MyTextWatcher(val view: TextView) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if(s.length >= 10){
            view.setText("Длина строки не более 10 символов")
            view.setTextColor(Color.RED)
        }
        else {
            view.setTextColor(Color.BLACK)
            view.setText("Введите данные")}
    }

    override fun afterTextChanged(s: Editable) {}
}
