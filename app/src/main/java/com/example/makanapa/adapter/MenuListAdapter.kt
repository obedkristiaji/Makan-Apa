package com.example.makanapa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import com.example.makanapa.FragmentListener
import com.example.makanapa.databinding.MenuListBinding
import com.example.makanapa.model.Menu

class MenuListAdapter(context: Context, listener: FragmentListener): BaseAdapter() {
    private var MenuList: List<Menu> = ArrayList()
    private var view: Context = context
    private var listener: FragmentListener = listener

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
            viewHolder = ViewHolder(itemView, listener)
            itemView.tag = viewHolder
        } else {
            itemView = view
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.updateView(this.getItem(position), position)
        return itemView
    }

    private class ViewHolder(view: View, listener: FragmentListener) {
        private val binding: MenuListBinding = MenuListBinding.bind(view)
        private val listener: FragmentListener = listener

        fun updateView(menu: Menu, position: Int) {
            this.binding.tvMenu.text = menu.getTitle()

            this.binding.tvMenu.setOnClickListener {
                this.listener.onMenuListClick(position, menu.getTitle(), menu.getDesc(), menu.getTag(), menu.getBahan(), menu.getCara(), menu.getResto())
            }
        }
    }
}