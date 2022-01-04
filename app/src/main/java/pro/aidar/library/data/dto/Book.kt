package pro.aidar.library.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String? = null,
    var size: String? = null,
    var updateDate: String? = null,
    var author: String? = null,
    var genre: String? = null
)
