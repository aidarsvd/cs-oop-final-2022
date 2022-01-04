package pro.aidar.library.utils

import androidx.documentfile.provider.DocumentFile

fun DocumentFile.size(): String {
    val size = length() / 1024
    return if (size < 1024)
        size.toString() + "КБ"
    else
        (size / 1024).toString() + "МБ"
}