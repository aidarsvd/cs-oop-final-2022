package pro.aidar.library.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import pro.aidar.library.R
import pro.aidar.library.databinding.ActivityMainBinding
import pro.aidar.library.utils.toggleVisible

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var searchView: SearchView
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                return true
            }
        })
        searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?) = true
}