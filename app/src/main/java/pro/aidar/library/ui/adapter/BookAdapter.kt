package pro.aidar.library.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList
import pro.aidar.library.data.dto.Book
import pro.aidar.library.databinding.ItemBookBinding
import pro.aidar.library.utils.toMb

class BookAdapter(
    val onBookClick: (uri: String) -> Unit,
    val onMoreClick: (view: View, book: Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private val books: ArrayList<Book?> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.BookViewHolder {
        return BookViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BookAdapter.BookViewHolder, position: Int) {
        holder.onBind(books[position])
    }

    override fun getItemCount() = books.size

    fun addBooks(list: List<Book>) {
        books.clear()
        books.addAll(list)
        notifyDataSetChanged()
    }

    inner class BookViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: Book?) {
            model?.let { book ->
                binding.bookName.text = book.name
                binding.bookSize.text = book.size?.toMb()
                itemView.setOnClickListener {
                    book.bookUri?.let { uri -> onBookClick(uri) }
                }
                binding.more.setOnClickListener {
                    onMoreClick(it, book)
                }
            }
        }
    }
}