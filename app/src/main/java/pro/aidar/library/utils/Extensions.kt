package pro.aidar.library.utils

import android.view.View
import androidx.core.view.isVisible
import androidx.documentfile.provider.DocumentFile

fun DocumentFile.size(): String {
    val size = length() / 1024
    return if (size < 1024)
        size.toString() + "КБ"
    else
        (size / 1024).toString() + "МБ"
}

fun View.toggleVisible(){
    this.isVisible = !this.isVisible
}