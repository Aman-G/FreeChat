package com.example.freechat.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.widget.ProgressBar

private var progressBar: Dialog? = null

fun Context.showAlertDialog(title: String, description: String, onPositiveClick: ()->Unit) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(description)
    builder.setCancelable(false)
    builder.setPositiveButton("Ok") { _, _ -> onPositiveClick() }
    builder.show()
}

fun Context.showProgressDialog() {
    progressBar = Dialog(this)
    progressBar!!.apply {
        setContentView(ProgressBar(context))
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    progressBar!!.show()
}

fun Context.hideProgressDialog() {
    progressBar?.let {
        if (it.isShowing) {
            it.dismiss()
        }
        progressBar = null;
    }
}