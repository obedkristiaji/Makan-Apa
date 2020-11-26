package com.example.makanapa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.makanapa.databinding.MenuListBinding
import com.example.makanapa.model.Menu
import com.example.makanapa.viewmodel.MainActivityViewModel

class MenuListAdapter(context: Context, viewModel: MainActivityViewModel): BaseAdapter() {
    private var MenuList: List<Menu> = ArrayList()
    private var view: Context = context
    private var viewModel = viewModel

    fun update(menu: List<Menu>) {
        this.MenuList = menu
        this.notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return MenuList.size
    }

    override fun getItem(position: Int): Menu {
        return MenuList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position : Int, view : View?, parent : ViewGroup) : View {
        val itemView: View
        val viewHolder: ViewHolder

        if (view == null) {
            itemView = MenuListBinding.inflate(LayoutInflater.from(this.view)).root
            viewHolder = ViewHolder(itemView, viewModel)
            itemView.tag = viewHolder
        } else {
            itemView = view
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.updateView(this.getItem(position), position, this.MenuList)
        return itemView
    }

    private class ViewHolder(view: View, viewModel: MainActivityViewModel) {
        private val binding: MenuListBinding = MenuListBinding.bind(view)
        private val viewModel: MainActivityViewModel = viewModel

        fun updateView(menu: Menu, position: Int, menuList: List<Menu>) {
            this.binding.tvMenu.text = menu.getTitle()
            this.viewModel.darkText(this.viewModel.dark.value!!, this.binding.tvMenu)

            this.binding.tvMenu.setOnClickListener {
                this.viewModel.updateSearch(menuList, position)
                this.viewModel.changePage("Search")
            }

            this.binding.ibEdit.setOnClickListener {
                this.viewModel.updateSearch(menuList, position)
                this.viewModel.returnPostion(position)
                this.viewModel.changePage("Edit")
            }

            this.binding.ibDelete.setOnClickListener {
                this.viewModel.deleteMenu(menuList, position)
                this.viewModel.changePage("Menu")
            }
        }
    }
}