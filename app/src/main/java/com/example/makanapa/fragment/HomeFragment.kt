package com.example.makanapa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.makanapa.databinding.FragmentMainBinding
import com.example.makanapa.viewmodel.MainActivityViewModel

class MainFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainActivityViewModel

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

        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        this.binding.btnMain.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        viewModel.size.observe(this, Observer<Int> { size ->
            if(size != 0){
                this.viewModel.randomPosition()
                viewModel.position.observe(viewLifecycleOwner, Observer<Int> { position ->
                    this.viewModel.updateSearch(position)
                })
            }
        })
        this.viewModel.changePage("Search")
    }
}