package pro.aidar.library.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.documentfile.provider.DocumentFile
import com.tbruyelle.rxpermissions2.RxPermissions
import java.util.Locale
import pro.aidar.library.R

fun Long.toMb(): String {
    val size = this / 1024
    return if (size < 1024)
        size.toString() + "КБ"
    else
        (size / 1024).toString() + "МБ"
}

fun View.toggleVisible() {
    this.isVisible = !this.isVisible
}

@SuppressLint("CheckResult")
fun AppCompatActivity.rxRequestPermissions(
    permissions: String,
    onRequestGranted: () -> Unit,
    onRequestNotGranted: () -> Unit = {},
    onRequestDenied: () -> Unit = {}
) {
    try {
        RxPermissions(this)
            .requestEach(permissions)
            .subscribe({ result ->
                when {
                    result.granted -> {
                        onRequestGranted()
                    }
                    result.shouldShowRequestPermissionRationale -> {
                        onRequestNotGranted()
                    }
                    else -> {
                        this.showWarningDialog(
                            content = this.getString(R.string.permission_required),
                            onOkListener = { this.openApplicationSettings(); onRequestDenied() })
                    }
                }
            }, { it.printStackTrace() })
    } catch (e: Exception) {
        e.printStackTrace()
        onRequestNotGranted()
    }
}

fun Context.showWarningDialog(content: CharSequence, onOkListener: () -> Unit = { }): Dialog {
    val builder = AlertDialog.Builder(this)
    builder.setMessage(content)
        .setCancelable(false)
        .setPositiveButton(resources.getString(R.string.ok)) { dialog, _ ->
            dialog.dismiss()
            onOkListener()
        }
    val dialog = builder.create()
    dialog.show()
    return dialog
}

fun Activity.openApplicationSettings() {
    this.startActivityForResult(
        Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:$packageName")
        ), 9000
    )
}

fun Context.showMessage(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun DocumentFile.isPdf(): Boolean {
    val extension = name!!.substringAfterLast('.', "").uppercase(Locale.getDefault())
    return extension == "PDF"
}