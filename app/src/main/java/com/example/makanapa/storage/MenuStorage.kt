package com.example.makanapa.storage

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.example.makanapa.model.Menu
import com.google.gson.reflect.TypeToken

class MenuStorage(context: Context) {
    protected lateinit var sharedPref: SharedPreferences
    companion object {
        protected var NAMA_SHARED_PREF: String = "sp_menu"
        protected var KEY_MENU: String = "LIST_MENU"
        protected var KEY_DARK: String = "DARK_MODE"
    }

    init {
        this.sharedPref = context.getSharedPreferences(NAMA_SHARED_PREF, 0)
    }

    fun clearMenu() {
        this.sharedPref.edit().putString(KEY_MENU, "[]").commit()
    }

    fun saveMenu(listMenu: List<Menu>) {
        val menu = Gson().toJson(listMenu)
        val editor: SharedPreferences.Editor = this.sharedPref.edit()
        editor.putString(KEY_MENU, menu)
        editor.commit()
    }

    fun getMenu(): List<Menu> {
        val menu = this.sharedPref.getString(KEY_MENU, "")
        val token = object : TypeToken<List<Menu>>() { }.type

        return Gson().fromJson(menu, token) ?: listOf()
    }

    fun saveDark(dark: Boolean) {
        val editor: SharedPreferences.Editor = this.sharedPref.edit()
        editor.putBoolean(KEY_DARK, dark)
        editor.commit()
    }

    fun getDark(): Boolean {
        return sharedPref.getBoolean(KEY_DARK, false)
    }
}