package com.example.makanapa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.makanapa.adapter.LeftListAdapter
import com.example.makanapa.databinding.FragmentLeftBinding
import com.example.makanapa.model.Left
import com.example.makanapa.viewmodel.MainActivityViewModel

class LeftFragment : Fragment() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: FragmentLeftBinding

    init {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeftBinding.inflate(inflater, container, false)

        val left: ListView = binding.lstLeft as ListView
        val listLeft = listOf<Left>(
            Left("Home", 1),
            Left("Recent", 2),
            Left("Menu", 3),
            Left("Setting", 4),
            Left("Exit", 5)
        )

        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        val adapter = LeftListAdapter(activity!!, listLeft, this.viewModel)
        left.adapter = adapter

        return binding.root
    }
}