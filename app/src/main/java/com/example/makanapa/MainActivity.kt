package com.example.makanapa

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.makanapa.databinding.ActivityMainBinding
import com.example.makanapa.fragment.*
import com.example.makanapa.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentHome: HomeFragment
    private lateinit var fragmentMenu: MenuFragment
    private lateinit var fragmentSearch: SearchFragment
    private lateinit var fragmentSetting: SettingFragment
    private lateinit var fragmentAdd: AddFragment
    private lateinit var fragmentEdit: EditFragment
    private lateinit var viewModel: MainActivityViewModel
    private var size: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        this.setSupportActionBar(binding.toolbar)
        val abdt: ActionBarDrawerToggle = ActionBarDrawerToggle(this,drawer_layout,binding.toolbar,R.string.openDrawer,R.string.closeDrawer)
        drawer_layout.addDrawerListener(abdt)
        abdt.syncState()

        this.fragmentHome = HomeFragment()
        this.fragmentMenu = MenuFragment()
        this.fragmentSearch = SearchFragment()
        this.fragmentSetting = SettingFragment()
        this.fragmentAdd = AddFragment()
        this.fragmentEdit = EditFragment()
        this.fragmentManager = this.supportFragmentManager

        viewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]

        viewModel.page.observe(this, Observer<String> { page ->
            this.changePage(page)
        })

        viewModel.open.observe(this, Observer<Boolean> { open ->
            this.closeApplication(open)
        })

        viewModel.openKey.observe(this, Observer<Boolean> { openKey ->
            this.closeKeyboard(openKey)
        })

        viewModel.dark.observe(this, Observer<Boolean> { dark ->
            this.viewModel.darkBackground(dark, this.binding.llMain)
        })

        viewModel.size.observe(this, Observer<Int> { size ->
            this.size = size
        })
    }

    fun changePage(page: String) {
        val ft: FragmentTransaction = this.fragmentManager.beginTransaction()
        when(page) {
            "Home" -> {
                this.fragmentManager.popBackStackImmediate()
                ft.replace(binding.fragmentContainer.id, this.fragmentHome)
            }
            "Search" -> {
                if(this.size > 0){
                    ft.replace(binding.fragmentContainer.id, this.fragmentSearch).addToBackStack(null)
                } else {
                    this.changePage("Menu")
                    Toast.makeText(this, "Masukkan menu terlebih dahulu!", Toast.LENGTH_SHORT).show()
                }
            }
            "Menu" -> {
                this.fragmentManager.popBackStackImmediate()
                ft.replace(binding.fragmentContainer.id, this.fragmentMenu).addToBackStack(null)
            }
            "Setting" -> {
                this.fragmentManager.popBackStackImmediate()
                ft.replace(binding.fragmentContainer.id, this.fragmentSetting).addToBackStack(null)
            }
            "Add" -> {
                ft.replace(binding.fragmentContainer.id, this.fragmentAdd).addToBackStack(null)
            }
            "Edit" -> {
                ft.replace(binding.fragmentContainer.id, this.fragmentEdit).addToBackStack(null)
            }
        }
        this.drawer_layout.closeDrawers()
        ft.commit()
    }

    fun closeApplication(open: Boolean) {
        if(!open) {
            this.moveTaskToBack(true)
            this.finish()
        }
    }

    fun closeKeyboard(openKey: Boolean) {
        if(!openKey) {
            val view: View? = this.currentFocus
            if (view != null) {
                val imm: InputMethodManager =
                    this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }
}