package com.example.movienew.core.extensions

import android.app.AlertDialog
import android.content.Context
import android.view.View
import com.example.movienew.R
import com.example.movienew.databinding.DialogViewBinding

fun Context.loadDialog() {

    val viewDialog = View.inflate(this, R.layout.dialog_view, null)
    val dialog =  AlertDialog.Builder(this).setView(viewDialog).create()

    dialog.show()
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

    val binding: DialogViewBinding = DialogViewBinding.bind(viewDialog)
    binding.txtMessage.text = this.getString(R.string.app_exit_app)

    binding.btnOk.setOnClickListener {
        dialog.dismiss()
    }

}
