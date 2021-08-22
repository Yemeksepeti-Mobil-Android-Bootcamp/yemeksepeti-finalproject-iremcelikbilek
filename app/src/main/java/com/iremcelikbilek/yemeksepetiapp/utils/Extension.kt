package com.iremcelikbilek.yemeksepetiapp.utils

import android.app.AlertDialog
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.iremcelikbilek.yemeksepetiapp.R

fun View.show() {
    visibility = View.VISIBLE
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

fun ImageView.loadFromUrl(imageUrl: String) {
    Glide.with(this).load(imageUrl).placeholder(R.drawable.loading).error(R.drawable.not_found).into(this)
}

inline fun <T> ifNotNull(obj: T?, block: (T) -> Unit) {
    if (obj != null) {
        block(obj)
    }
}

