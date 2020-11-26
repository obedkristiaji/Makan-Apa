package com.example.makanapa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.makanapa.databinding.FragmentSearchBinding
import com.example.makanapa.viewmodel.MainActivityViewModel

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: MainActivityViewModel

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

        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        viewModel.dark.observe(viewLifecycleOwner, Observer<Boolean> { dark ->
            this.viewModel.darkText(dark, this.binding.tvTitle)
            this.viewModel.darkText(dark, this.binding.tvDesc1)
            this.viewModel.darkText(dark, this.binding.tvDesc2)
            this.viewModel.darkText(dark, this.binding.tvTag1)
            this.viewModel.darkText(dark, this.binding.tvTag2)
            this.viewModel.darkText(dark, this.binding.tvBahan1)
            this.viewModel.darkText(dark, this.binding.tvBahan2)
            this.viewModel.darkText(dark, this.binding.tvCara1)
            this.viewModel.darkText(dark, this.binding.tvCara2)
            this.viewModel.darkText(dark, this.binding.tvResto1)
            this.viewModel.darkText(dark, this.binding.tvResto2)
        })

        viewModel.title.observe(viewLifecycleOwner, Observer<String> { title ->
            this.binding.tvTitle.text = title
        })
        viewModel.desc.observe(viewLifecycleOwner, Observer<String> { desc ->
            this.binding.tvDesc2.text = desc
        })
        viewModel.tag.observe(viewLifecycleOwner, Observer<String> { tag ->
            this.binding.tvTag2.text = tag
        })
        viewModel.bahan.observe(viewLifecycleOwner, Observer<String> { bahan ->
            this.binding.tvBahan2.text = bahan
        })
        viewModel.cara.observe(viewLifecycleOwner, Observer<String> { cara ->
            this.binding.tvCara2.text = cara
        })
        viewModel.resto.observe(viewLifecycleOwner, Observer<String> { resto ->
            this.binding.tvResto2.text = resto
        })

        return view
    }
}