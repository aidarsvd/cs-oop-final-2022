package pro.aidar.library.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String? = null,
    var size: Int? = null,
    var updateDate: Date? = null,
    var author: String? = null,
    var genre: String? = null
)
