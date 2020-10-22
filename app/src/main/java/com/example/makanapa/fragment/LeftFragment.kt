package com.example.makanapa.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.makanapa.FragmentListener
import com.example.makanapa.adapter.LeftListAdapter
import com.example.makanapa.databinding.FragmentLeftBinding
import com.example.makanapa.model.Left

class LeftFragment : Fragment() {
    private lateinit var fl: FragmentListener

    init {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLeftBinding.inflate(inflater, container, false)

        val left: ListView = binding.lstLeft as ListView
        val listLeft = listOf<Left>(
            Left("Home", 1),
            Left("Search", 2),
            Left("Menu", 3),
            Left("Setting", 4),
            Left("Exit", 5)
        )

        val adapter = LeftListAdapter(activity!!, listLeft, this.fl)
        left.adapter = adapter

//        this.binding.btnHome.setOnClickListener(this)
//        this.binding.btnP2.setOnClickListener(this)
//        this.binding.btnExit.setOnClickListener(this)

        return binding.root
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

//    override fun onClick(v: View?) {
//        if(v == this.binding.btnHome) {
//            this.fl.changePage(1)
//        }else if(v == this.binding.btnP2) {
//            this.fl.changePage(2)
//        }else {
//            this.fl.closeApplication()
//        }
//    }
}