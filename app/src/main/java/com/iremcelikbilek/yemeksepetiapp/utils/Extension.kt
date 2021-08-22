package com.iremcelikbilek.yemeksepetiapp.utils

import android.app.AlertDialog
import android.view.View
import androidx.fragment.app.Fragment

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Fragment.showAlert(message: String?) {
    AlertDialog.Builder(context)
        .setTitle("Error")
        .setMessage(message)
        .setPositiveButton("ok") { dialog, button ->
            dialog.dismiss()
        }.show()
}