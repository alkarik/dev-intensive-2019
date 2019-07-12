package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val cfocus=this.currentFocus
    if(cfocus!=null) {
        val imm = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(cfocus.windowToken, 0)
    }
}
