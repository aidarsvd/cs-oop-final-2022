package pro.aidar.library.ui.bottom_sheet

import pro.aidar.library.data.dto.Book

interface BottomSheetListener {
    fun onSave(book: Book)
    fun onDelete(book: Book)
}