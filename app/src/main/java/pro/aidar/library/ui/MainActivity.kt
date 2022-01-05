package pro.aidar.library.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.documentfile.provider.DocumentFile
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import pro.aidar.library.R
import pro.aidar.library.data.dto.Book
import pro.aidar.library.data.dto.Event
import pro.aidar.library.databinding.ActivityMainBinding
import pro.aidar.library.ui.adapter.BookAdapter
import pro.aidar.library.ui.bottom_sheet.BottomSheetListener
import pro.aidar.library.ui.bottom_sheet.EditBookBottomFragment
import pro.aidar.library.ui.bottom_sheet.EditBookBottomFragment.Companion.newInstance
import pro.aidar.library.ui.bottom_sheet.InfoBookBottomFragment
import pro.aidar.library.ui.bottom_sheet.InfoBookBottomFragment.Companion.newInstance
import pro.aidar.library.utils.displayPopUp
import pro.aidar.library.utils.isPdf
import pro.aidar.library.utils.rxRequestPermissions
import pro.aidar.library.utils.showMessage
import pro.aidar.library.utils.toggleVisible

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener, BottomSheetListener {
    private lateinit var searchView: SearchView
    private val binding: ActivityMainBinding by viewBinding()
    private val adapter = BookAdapter(::onBookClick, ::onMoreClick)
    private val viewModel: MainViewModel by viewModels()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
        registerActivityResult()
        subscribeToLiveData()
        initClick()
        viewModel.fetchBooks()
    }

    private fun initClick() {
        binding.addBook.setOnClickListener {
            rxRequestPermissions(onRequestGranted = {
                pickFile()
            })
        }
        binding.date.setOnClickListener {
            viewModel.fetchBooks(MainViewModel.Orders.DATE)
        }

        binding.name.setOnClickListener {
            viewModel.fetchBooks(MainViewModel.Orders.NAME)
        }

        binding.size.setOnClickListener {
            viewModel.fetchBooks(MainViewModel.Orders.SIZE)
        }
    }

    private fun subscribeToLiveData() {
        viewModel.event.observe(this, {
            when (it) {
                is Event.BookInserted -> viewModel.fetchBooks()
                is Event.BookInsertError -> showMessage("Can not add book")
                is Event.BooksFetched -> adapter.addBooks(it.list)
            }
        })
    }

    private fun onBookClick(uri: String) {
        startActivity(
            Intent(this, PdfActivity::class.java)
                .putExtra("URI", uri)
        )
    }

    private fun onMoreClick(view: View, model: Book) {
        displayPopUp(
            view = view,
            edit = {
                newInstance(model, this).show(supportFragmentManager, EditBookBottomFragment.TAG)
            },
            info = {
                newInstance(model).show(supportFragmentManager, InfoBookBottomFragment.TAG)
            }
        )
    }

    private fun initAdapter() {
        binding.booksRecycler.adapter = adapter
    }

    private fun pickFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "*/*"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        resultLauncher.launch(intent)
    }

    private fun registerActivityResult() {
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri: Uri = result.data?.data!!
                val documentFile = DocumentFile.fromSingleUri(this, uri)
                documentFile?.let {
                    if (it.isPdf()) addBook(it, uri)
                    else showMessage(getString(R.string.doc_extension_error))
                } ?: run {
                    showMessage(getString(R.string.file_not_found))
                }
            }
        }
    }

    private fun addBook(it: DocumentFile, uri: Uri) {
        val book = Book(
            name = it.name,
            size = it.length(),
            updateDate = Calendar.getInstance().time,
            bookUri = uri.toString()
        )
        viewModel.addBook(model = book)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        val searchItem = menu?.findItem(R.id.action_search)

        val sortItem = menu?.findItem(R.id.action_sort)!!

        sortItem.setOnMenuItemClickListener {
            binding.sortView.toggleVisible()
            binding.sortGroup.clearCheck()
            true
        }

        searchItem?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                binding.sortView.isVisible = false
                binding.sortGroup.clearCheck()
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                binding.sortView.isVisible = false
                viewModel.fetchBooks()
                return true
            }
        })
        searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.searchBook(query!!)
        return true
    }

    override fun onQueryTextChange(newText: String?) = true

    override fun onSave(book: Book) {
        book
    }

    override fun onDelete(book: Book) {
    }
}