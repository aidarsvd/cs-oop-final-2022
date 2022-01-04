package pro.aidar.library.utils

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.documentfile.provider.DocumentFile
import com.tbruyelle.rxpermissions2.RxPermissions
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
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
    onRequestGranted: () -> Unit,
    onRequestNotGranted: () -> Unit = {},
    onRequestDenied: () -> Unit = {}
) {
    try {
        RxPermissions(this)
            .requestEachCombined(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE)
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

fun Uri.getFile(context: Context): File {
    val destinationFilename = File(context.filesDir.path + File.separatorChar + queryName(context, this))
    try {
        context.contentResolver.openInputStream(this).use { ins -> createFileFromStream(ins!!, destinationFilename) }
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return destinationFilename
}

private fun createFileFromStream(ins: InputStream, destination: File?) {
    try {
        FileOutputStream(destination).use { os ->
            val buffer = ByteArray(4096)
            var length: Int
            while (ins.read(buffer).also { length = it } > 0) {
                os.write(buffer, 0, length)
            }
            os.flush()
        }
    } catch (ex: java.lang.Exception) {
        ex.printStackTrace()
    }
}

private fun queryName(context: Context, uri: Uri): String {
    val returnCursor: Cursor = context.contentResolver.query(uri, null, null, null, null)!!
    val nameIndex: Int = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    returnCursor.moveToFirst()
    val name: String = returnCursor.getString(nameIndex)
    returnCursor.close()
    return name
}

fun Activity.displayPopUp(
    view: View,
    edit: () -> Unit,
    info: () -> Unit
) {
    val popup = PopupMenu(this, view)
    popup.inflate(R.menu.item_menu)
    popup.setOnMenuItemClickListener { item ->
        when (item.itemId) {
            R.id.edit -> {
                edit()
                true
            }
            else -> {
                info()
                true
            }
        }
    }
    popup.show()
}

@SuppressLint("SimpleDateFormat")
fun Date.toStringFormat(): String {
    val format = SimpleDateFormat("dd.MM.yyy")
    return format.format(this)
}