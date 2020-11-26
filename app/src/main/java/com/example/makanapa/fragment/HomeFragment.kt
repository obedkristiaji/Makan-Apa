package com.example.makanapa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.makanapa.databinding.FragmentHomeBinding
import com.example.makanapa.viewmodel.MainActivityViewModel

class HomeFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainActivityViewModel
    private var size = 0
    private var position = 0

    init {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View
        view = FragmentHomeBinding.inflate(inflater, container, false).root
        this.binding = FragmentHomeBinding.bind(view)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        viewModel.dark.observe(viewLifecycleOwner, Observer<Boolean> { dark ->
            this.viewModel.darkText(dark, this.binding.tvHome1)
            this.viewModel.darkText(dark, this.binding.tvHome2)
        })

        viewModel.size.observe(viewLifecycleOwner, Observer<Int> { size ->
            this.size = size
        })

        viewModel.position.observe(viewLifecycleOwner, Observer<Int> { position ->
            this.position = position
        })

        this.binding.btnHome.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        if(this.size > 0){
            this.viewModel.randomPosition()
            this.viewModel.updateSearch(this.viewModel.menuList.value!!, this.position)
        }
        this.viewModel.changePage("Search")
    }
}