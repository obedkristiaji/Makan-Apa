package com.example.makanapa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.makanapa.databinding.LeftListBinding
import com.example.makanapa.model.Left
import com.example.makanapa.viewmodel.MainActivityViewModel

class LeftListAdapter(context: Context, data: List<Left>, viewModel: MainActivityViewModel): ArrayAdapter<Left>(context, 0, data) {
    private var leftList: List<Left> = data
    private var view: Context = context
    private var viewModel = viewModel

    override fun getItem(position: Int): Left {
        return leftList[position]
    }

    override fun getView(position : Int, view : View?, parent : ViewGroup) : View {
        val itemView: View
        val viewHolder: ViewHolder

        if (view == null) {
            itemView = LeftListBinding.inflate(LayoutInflater.from(this.view)).root
            viewHolder = ViewHolder(itemView, viewModel)
            itemView.tag = viewHolder
        } else {
            itemView = view
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.updateView(this.getItem(position))
        return itemView
    }

    private class ViewHolder(view: View, viewModel: MainActivityViewModel) {
        private val binding: LeftListBinding = LeftListBinding.bind(view)
        private val viewModel: MainActivityViewModel = viewModel

        fun updateView(left: Left) {
            this.binding.tvLeft.text = left.getTitle()

            this.binding.tvLeft.setOnClickListener {
                when(left.getId()) {
                    1 -> this.viewModel.changePage("Home")
                    2 -> this.viewModel.changePage("Search")
                    3 -> this.viewModel.changePage("Menu")
                    4 -> this.viewModel.changePage("Setting")
                    5 -> this.viewModel.close()
                }
            }
        }
    }
}