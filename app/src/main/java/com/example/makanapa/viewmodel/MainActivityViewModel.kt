package com.example.makanapa.viewmodel

import android.app.Application
import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.makanapa.R
import com.example.makanapa.model.Menu
import com.example.makanapa.storage.MenuStorage
import com.example.makanapa.storage.MockMenu

class MainActivityViewModel(application: Application): AndroidViewModel(application) {
    val db: MenuStorage = MenuStorage(application)
    val page: MutableLiveData<String> = MutableLiveData()

    val title: MutableLiveData<String> = MutableLiveData()
    val desc: MutableLiveData<String> = MutableLiveData()
    val tag: MutableLiveData<String> = MutableLiveData()
    val bahan: MutableLiveData<String> = MutableLiveData()
    val cara: MutableLiveData<String> = MutableLiveData()
    val resto: MutableLiveData<String> = MutableLiveData()

    val open: MutableLiveData<Boolean> = MutableLiveData()
    val openKey: MutableLiveData<Boolean> = MutableLiveData()

    val menuList: MutableLiveData<List<Menu>> = MutableLiveData()
    val list: MutableList<Menu> = mutableListOf()
    val size: MutableLiveData<Int> = MutableLiveData()
    val menuSearch: MutableLiveData<List<Menu>> = MutableLiveData()
    val search: MutableLiveData<String> = MutableLiveData()

    val position: MutableLiveData<Int> = MutableLiveData()

    val dark: MutableLiveData<Boolean> = MutableLiveData()

    init {
        this.page.value = "Home"
        this.size.value = 0
        this.list.addAll(db.getMenu())
        this.update()
        this.dark.value = this.db.getDark()
        this.menuSearch.value = ArrayList()
        this.search.value = ""
    }

    fun update() {
        if(this.list.size == 0){
            this.list.addAll(MockMenu().menuObjectArr)
        }
        this.menuList.value = this.list
        this.updateSearch(this.menuList.value!!, this.menuList.value!!.size-1)
        this.updateSize(list.size)
    }

    fun changePage(page: String) {
        this.page.value = page
    }

    fun updateSearch(menu: List<Menu>, position: Int) {
        this.title.value = menu[position].getTitle()
        this.desc.value = menu[position].getDesc()
        this.tag.value = menu[position].getTag()
        this.bahan.value = menu[position].getBahan()
        this.cara.value = menu[position].getCara()
        this.resto.value = menu[position].getResto()
    }

    fun close() {
        this.open.value = false
    }

    fun closeKey() {
        this.openKey.value = false
    }

    fun addMenu(menu: Menu) {
        var baru: Boolean = true
        for(i in 0..this.size.value!!-1) {
            if(this.menuList.value!![i].getTitle() == menu.getTitle()) {
                this.changePage("Menu")
                Toast.makeText(getApplication(), "Sudah ada menu dengan nama ini!", Toast.LENGTH_SHORT).show()
                baru = false
                break
            }
        }
        if(baru){
            if(menu.getTitle() == ""){
                this.changePage("Menu")
                Toast.makeText(getApplication(), "Nama menu tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                this.menuList.value as ArrayList<Menu> += menu
                this.size.value = this.size.value!! + 1
                this.db.saveMenu(this.menuList.value!!)
                this.updateSearch(this.menuList.value!!, this.menuList.value!!.size-1)
            }
        }

        this.resetSearch()
        this.searchMenu(this.search.value!!)
    }

    fun deleteMenu(menu: List<Menu>, position: Int) {
        for(i in 0..this.size.value!!-1) {
            if (this.menuList.value!![i].getTitle() == menu[position].getTitle()) {
                this.menuList.value = this.menuList.value?.minus(this.menuList.value!![i])
                break
            }
        }
        this.size.value = this.size.value!!-1
        this.db.saveMenu(this.menuList.value!!)
        if(this.menuList.value!!.size != 0 ) {
            this.updateSearch(this.menuList.value!!, this.menuList.value!!.size - 1)
        }

        this.resetSearch()
        this.searchMenu(this.search.value!!)
    }

    fun editMenu(menu: List<Menu>, position: Int, title: String, desc: String, tag: String, bahan: String, cara: String, resto: String) {
        var baru: Boolean = true
        for(i in 0..this.size.value!!-1) {
            if(this.menuList.value!![i].getTitle() == title) {
                this.changePage("Menu")
                Toast.makeText(getApplication(), "Sudah ada menu dengan nama ini!", Toast.LENGTH_SHORT).show()
                baru = false
                break
            }
        }
        if(baru) {
            for (i in 0..this.size.value!! - 1) {
                if (this.menuList.value!![i].getTitle() == menu[position].getTitle()) {
                    if (!title.equals("")) {
                        this.menuList.value!![i].setTitle(title)
                    }
                    if (!desc.equals("")) {
                        this.menuList.value!![i].setDesc(desc)
                    }
                    if (!tag.equals("")) {
                        this.menuList.value!![i].setTag(tag)
                    }
                    if (!bahan.equals("")) {
                        this.menuList.value!![i].setBahan(bahan)
                    }
                    if (!cara.equals("")) {
                        this.menuList.value!![i].setCara(cara)
                    }
                    if (!resto.equals("")) {
                        this.menuList.value!![i].setResto(resto)
                    }
                    this.updateSearch(this.menuList.value!!, i)
                }
            }
            this.db.saveMenu(this.menuList.value!!)
        }

        this.resetSearch()
        this.searchMenu(this.search.value!!)
    }

    fun returnPostion(position: Int) {
        this.position.value = position
    }

    fun randomPosition() {
        this.position.value = (0..this.size.value!!-1).random()
    }

    fun updateSize(size: Int) {
        this.size.value = size
    }

    fun clearData() {
        this.db.clearMenu()
        this.menuList.value = this.db.getMenu()
        this.updateSize(0)
    }

    fun darkText(dark: Boolean, text: TextView) {
        if(dark) {
            text.setTextColor(Color.WHITE)
        } else {
            text.setTextColor(Color.BLACK)
        }
    }
    
    fun darkBackground(dark: Boolean, ll: LinearLayout) {
        if(dark) {
            ll.setBackgroundColor(Color.BLACK)
        } else {
            ll.setBackgroundColor(Color.WHITE)
        }
    }

    fun darkEdit(dark: Boolean, et: EditText) {
        if(dark) {
            et.setTextColor(Color.WHITE)
            et.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            et.setHintTextColor(ColorStateList.valueOf(Color.LTGRAY))
        } else {
            et.setTextColor(Color.BLACK)
            et.backgroundTintList = ColorStateList.valueOf(Color.BLACK)
            et.setHintTextColor(ColorStateList.valueOf(Color.DKGRAY))
        }
    }

    fun darkMode() {
        this.dark.value = !this.dark.value!!
        this.db.saveDark(this.dark.value!!)
    }

    fun searchMenu(text: String) {
        this.search.value = text
        if(text != ""){
            for(i in 0..this.size.value!!-1) {
                if(this.menuList.value!![i].getTitle().indexOf(text) != -1 || this.menuList.value!![i].getTag().indexOf(text) != -1 || this.menuList.value!![i].getBahan().indexOf(text) != -1){
                    this.menuSearch.value = this.menuSearch.value?.plus(this.menuList.value!![i])
                }
            }
        }
    }

    fun resetSearch() {
        this.menuSearch.value = ArrayList()
    }

    fun darkBtn(dark: Boolean, btn: Button) {
        if(dark) {
            btn.setText(R.string.setting_btn_light)
        } else {
            btn.setText(R.string.setting_btn_dark)
        }
    }
}