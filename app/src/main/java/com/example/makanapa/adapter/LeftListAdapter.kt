package com.example.makanapa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.makanapa.FragmentListener
import com.example.makanapa.databinding.LeftListBinding
import com.example.makanapa.model.Left

class LeftListAdapter(context: Context, data: List<Left>, listener: FragmentListener): ArrayAdapter<Left>(context, 0, data) {
    private var leftList: List<Left> = data
    private var view: Context = context
    private var listener: FragmentListener = listener

    override fun getItem(position: Int): Left {
        return leftList[position]
    }

    override fun getView(position : Int, view : View?, parent : ViewGroup) : View {
        val itemView: View
        val viewHolder: ViewHolder

        if (view == null) {
            itemView = LeftListBinding.inflate(LayoutInflater.from(this.view)).root
            viewHolder = ViewHolder(itemView, listener)
            itemView.tag = viewHolder
        } else {
            itemView = view
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.updateView(this.getItem(position))
        return itemView
    }

    private class ViewHolder(view: View, listener: FragmentListener) {
        private val binding: LeftListBinding = LeftListBinding.bind(view)
        private val listener: FragmentListener = listener

        fun updateView(left: Left) {
            this.binding.tvLeft.text = left.getTitle()

            this.binding.tvLeft.setOnClickListener {
                when(left.getId()) {
                    1 -> this.listener.changePage(1)
                    2 -> this.listener.changePage(2)
                    3 -> this.listener.changePage(3)
                    4 -> this.listener.changePage(4)
                    5 -> this.listener.closeApplication()
                }
            }
        }
    }
}