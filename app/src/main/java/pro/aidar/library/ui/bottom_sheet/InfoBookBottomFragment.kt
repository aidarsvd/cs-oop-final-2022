package pro.aidar.library.ui.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import org.parceler.Parcels
import pro.aidar.library.R
import pro.aidar.library.data.dto.Book
import pro.aidar.library.databinding.FragmentInfoBookBottomBinding
import pro.aidar.library.utils.toMb
import pro.aidar.library.utils.toStringFormat

class InfoBookBottomFragment : BottomSheetDialogFragment() {

    private lateinit var book: Book
    private val binding: FragmentInfoBookBottomBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info_book_bottom, container, false)
    }

    private fun setUpViews() {
        arguments?.let {
            book = Parcels.unwrap(it.getParcelable(BOOK))
        }
        book.run {
            binding.bookName.text = name
            binding.bookAuthor.text = author ?: getString(R.string.none_stub)
            binding.bookGenre.text = genre ?: getString(R.string.none_stub)
            binding.bookSize.text = book.size!!.toMb()
            binding.bookUpdate.text = book.updateDate!!.toStringFormat()
        }
    }

    companion object {
        const val TAG = "InfoBookBottomFragment"
        private const val BOOK = "ID_DOC"
        fun newInstance(model: Book) = InfoBookBottomFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BOOK, Parcels.wrap(model))
            }
        }
    }
}