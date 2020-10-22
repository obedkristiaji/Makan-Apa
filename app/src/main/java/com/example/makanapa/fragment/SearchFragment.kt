package com.example.makanapa.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.makanapa.FragmentListener
import com.example.makanapa.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var fl: FragmentListener

    init {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View
        view = FragmentSearchBinding.inflate(inflater, container, false).root
        this.binding = FragmentSearchBinding.bind(view)
        this.binding.tvTitle.text = this.getArguments()!!.getString("title")
        this.binding.tvDesc.text = this.getArguments()!!.getString("desc")
        this.binding.tvTag.text = this.getArguments()!!.getString("tag")
        this.binding.tvBahan.text = this.getArguments()!!.getString("bahan")
        this.binding.tvCara.text = this.getArguments()!!.getString("cara")
        this.binding.tvResto.text = this.getArguments()!!.getString("resto")
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener) {
            this.fl = context as FragmentListener
        } else {
            throw ClassCastException(context.toString()
                    + " must implement FragmentListener")
        }
    }

    companion object {
        fun newInstance(title: String, desc: String, tag: String, bahan: String, cara: String, resto: String): SearchFragment {
            val fragment: SearchFragment = SearchFragment()
            val args: Bundle = Bundle()
            args.putString("title", title)
            args.putString("desc", desc)
            args.putString("tag", tag)
            args.putString("bahan", bahan)
            args.putString("cara", cara)
            args.putString("resto", resto)
            fragment.setArguments(args)
            return fragment
        }
    }
}