package pro.aidar.library.ui.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.parceler.Parcels
import pro.aidar.library.R
import pro.aidar.library.data.dto.Book
import pro.aidar.library.databinding.FragmentEditBookBottomBinding
import pro.aidar.library.utils.toMb
import pro.aidar.library.utils.toStringFormat

class EditBookBottomFragment : BottomSheetDialogFragment() {

    private lateinit var book: Book
    private val binding: FragmentEditBookBottomBinding by viewBinding()
    private lateinit var listener: BottomSheetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_book_bottom, container, false)
    }

    private fun setUpViews() {
        arguments?.let {
            book = Parcels.unwrap(it.getParcelable(BOOK))
        }

        book.run {
            binding.bookName.setText(name)
            binding.bookAuthor.setText(author ?: "")
            binding.bookGenre.setText(genre ?: "")
            binding.bookSize.text = book.size!!.toMb()
            binding.bookUpdate.text = book.updateDate!!.toStringFormat()
            binding.saveBtn.setOnClickListener { listener.onSave(this) }
            binding.deleteBtn.setOnClickListener { listener.onDelete(this) }
        }
    }

    private fun toggleButton() {
    }

    companion object {
        const val TAG = "InfoBookBottomFragment"
        private const val BOOK = "ID_DOC"
        fun newInstance(model: Book, clickListener: BottomSheetListener) = EditBookBottomFragment().apply {
            arguments = Bundle().apply {
                listener = clickListener
                putParcelable(BOOK, Parcels.wrap(model))
            }
        }
    }
}