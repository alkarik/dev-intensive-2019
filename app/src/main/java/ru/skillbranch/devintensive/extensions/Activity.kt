package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val cfocus=this.currentFocus
    if(cfocus!=null) {
        val imm = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(cfocus.windowToken, 0)
    }
}

fun Activity.isKeyboardOpen(): Boolean {
    val visibleBounds = Rect()
    val rootView = window.decorView
    rootView.getWindowVisibleDisplayFrame(visibleBounds)
    return rootView.height>visibleBounds.height()
}

fun Activity.isKeyboardClosed(): Boolean {
    return !this.isKeyboardOpen()
}