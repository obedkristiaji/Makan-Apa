package com.example.makanapa.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.makanapa.FragmentListener
import com.example.makanapa.databinding.FragmentMainBinding

class MainFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentMainBinding
    private lateinit var fl: FragmentListener

    init {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View
        view = FragmentMainBinding.inflate(inflater, container, false).root
        this.binding = FragmentMainBinding.bind(view)
        this.binding.btnMain.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        this.fl.changePage(2)
    }
}