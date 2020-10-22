package com.example.makanapa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.makanapa.databinding.ActivityMainBinding
import com.example.makanapa.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FragmentListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentM: MainFragment
    private lateinit var fragmentMenu: MenuFragment
    private lateinit var fragmentSearch: SearchFragment
    private lateinit var fragmentSetting: SettingFragment
    private lateinit var fragmentAdd: AddFragment
    private lateinit var fragmentEdit: EditFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        this.setSupportActionBar(binding.toolbar)
        val abdt: ActionBarDrawerToggle = ActionBarDrawerToggle(this,drawer_layout,binding.toolbar,R.string.openDrawer,R.string.closeDrawer)
        drawer_layout.addDrawerListener(abdt)
        abdt.syncState()

        this.fragmentM = MainFragment()
        this.fragmentMenu = MenuFragment()
        this.fragmentSearch = SearchFragment()
        this.fragmentSetting = SettingFragment()
        this.fragmentAdd = AddFragment()
        this.fragmentEdit = EditFragment()
        this.fragmentManager = this.supportFragmentManager
        this.changePage(1)
    }

    override fun changePage(page: Int) {
        val ft: FragmentTransaction = this.fragmentManager.beginTransaction()
        if(page == 1) {
            if(this.fragmentM.isAdded()) {
                ft.show(this.fragmentM)
            } else {
                ft.add(binding.fragmentContainer.id, this.fragmentM)
            }
            if(this.fragmentMenu.isAdded()) {
                ft.hide(this.fragmentMenu)
            }
            if(this.fragmentSearch.isAdded()) {
                ft.hide(this.fragmentSearch)
            }
            if(this.fragmentSetting.isAdded()) {
                ft.hide(this.fragmentSetting)
            }
            if(this.fragmentAdd.isAdded()) {
                ft.hide(this.fragmentAdd)
            }
            if(this.fragmentEdit.isAdded()) {
                ft.hide(this.fragmentEdit)
            }
        } else if(page == 2) {
            if(this.fragmentSearch.isAdded()) {
                ft.show(this.fragmentSearch)
            } else {
                ft.add(binding.fragmentContainer.id, this.fragmentSearch)
            }
            if(this.fragmentMenu.isAdded()) {
                ft.hide(this.fragmentMenu)
            }
            if(this.fragmentM.isAdded()) {
                ft.hide(this.fragmentM)
            }
            if(this.fragmentSetting.isAdded()) {
                ft.hide(this.fragmentSetting)
            }
            if(this.fragmentAdd.isAdded()) {
                ft.hide(this.fragmentAdd)
            }
            if(this.fragmentEdit.isAdded()) {
                ft.hide(this.fragmentEdit)
            }
        } else if(page == 3) {
            if(this.fragmentMenu.isAdded()) {
                ft.show(this.fragmentMenu)
            } else {
                ft.add(binding.fragmentContainer.id, this.fragmentMenu)
            }
            if(this.fragmentM.isAdded()) {
                ft.hide(this.fragmentM)
            }
            if(this.fragmentSearch.isAdded()) {
                ft.hide(this.fragmentSearch)
            }
            if(this.fragmentSetting.isAdded()) {
                ft.hide(this.fragmentSetting)
            }
            if(this.fragmentAdd.isAdded()) {
                ft.hide(this.fragmentAdd)
            }
            if(this.fragmentEdit.isAdded()) {
                ft.hide(this.fragmentEdit)
            }
        } else if(page == 4) {
            if(this.fragmentSetting.isAdded()) {
                ft.show(this.fragmentSetting)
            } else {
                ft.add(binding.fragmentContainer.id, this.fragmentSetting)
            }
            if(this.fragmentMenu.isAdded()) {
                ft.hide(this.fragmentMenu)
            }
            if(this.fragmentM.isAdded()) {
                ft.hide(this.fragmentM)
            }
            if(this.fragmentSearch.isAdded()) {
                ft.hide(this.fragmentSearch)
            }
            if(this.fragmentAdd.isAdded()) {
                ft.hide(this.fragmentAdd)
            }
            if(this.fragmentEdit.isAdded()) {
                ft.hide(this.fragmentEdit)
            }
        } else if(page == 5) {
            if(this.fragmentAdd.isAdded()) {
                ft.show(this.fragmentAdd)
            } else {
                ft.add(binding.fragmentContainer.id, this.fragmentAdd)
            }
            if(this.fragmentMenu.isAdded()) {
                ft.hide(this.fragmentMenu)
            }
            if(this.fragmentM.isAdded()) {
                ft.hide(this.fragmentM)
            }
            if(this.fragmentSearch.isAdded()) {
                ft.hide(this.fragmentSearch)
            }
            if(this.fragmentSetting.isAdded()) {
                ft.hide(this.fragmentSetting)
            }
            if(this.fragmentEdit.isAdded()) {
                ft.hide(this.fragmentEdit)
            }
        } else if(page == 6) {
            if(this.fragmentEdit.isAdded()) {
                ft.show(this.fragmentEdit)
            } else {
                ft.add(binding.fragmentContainer.id, this.fragmentEdit)
            }
            if(this.fragmentMenu.isAdded()) {
                ft.hide(this.fragmentMenu)
            }
            if(this.fragmentM.isAdded()) {
                ft.hide(this.fragmentM)
            }
            if(this.fragmentSearch.isAdded()) {
                ft.hide(this.fragmentSearch)
            }
            if(this.fragmentSetting.isAdded()) {
                ft.hide(this.fragmentSetting)
            }
            if(this.fragmentAdd.isAdded()) {
                ft.hide(this.fragmentAdd)
            }
        }
        this.drawer_layout.closeDrawers()
        ft.commit()
    }

    override fun closeApplication() {
        this.moveTaskToBack(true)
        this.finish()
    }

    override fun onMenuListClick(position: Int, title: String, desc: String, tag: String, bahan: String, cara: String, resto: String) {
        val ft: FragmentTransaction = this.fragmentManager.beginTransaction()
        this.fragmentSearch = SearchFragment.newInstance(title, desc, tag, bahan, cara, resto)
        if(this.fragmentSearch.isAdded()) {
            ft.show(this.fragmentSearch)
        } else {
            ft.add(binding.fragmentContainer.id, this.fragmentSearch)
        }
        if(this.fragmentMenu.isAdded()) {
            ft.hide(this.fragmentMenu)
        }
        if(this.fragmentM.isAdded()) {
            ft.hide(this.fragmentM)
        }
        if(this.fragmentSetting.isAdded()) {
            ft.hide(this.fragmentSetting)
        }
        if(this.fragmentAdd.isAdded()) {
            ft.hide(this.fragmentAdd)
        }
        if(this.fragmentEdit.isAdded()) {
            ft.hide(this.fragmentEdit)
        }
        ft.commit()
    }
}