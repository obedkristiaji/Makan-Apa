package com.example.makanapa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.makanapa.adapter.MenuListAdapter
import com.example.makanapa.databinding.FragmentAddBinding
import com.example.makanapa.model.Menu
import com.example.makanapa.viewmodel.MainActivityViewModel

class AddFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: MainActivityViewModel

    init {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View
        view = FragmentAddBinding.inflate(inflater, container, false).root
        this.binding = FragmentAddBinding.bind(view)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        viewModel.dark.observe(viewLifecycleOwner, Observer<Boolean> { dark ->
            this.viewModel.darkText(dark, this.binding.tvAddTitle)
            this.viewModel.darkText(dark, this.binding.tvAddDesc)
            this.viewModel.darkText(dark, this.binding.tvAddTag)
            this.viewModel.darkText(dark, this.binding.tvAddBahan)
            this.viewModel.darkText(dark, this.binding.tvAddCara)
            this.viewModel.darkText(dark, this.binding.tvAddResto)

            this.viewModel.darkEdit(dark, this.binding.etAddTitle)
            this.viewModel.darkEdit(dark, this.binding.etAddDesc)
            this.viewModel.darkEdit(dark, this.binding.etAddTag)
            this.viewModel.darkEdit(dark, this.binding.etAddBahan)
            this.viewModel.darkEdit(dark, this.binding.etAddCara)
            this.viewModel.darkEdit(dark, this.binding.etAddResto)
        })

        this.binding.fabAddSave.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        this.viewModel.addMenu(Menu(this.binding.etAddTitle.text.toString().toUpperCase(), this.binding.etAddDesc.text.toString(), this.binding.etAddTag.text.toString().toUpperCase(), this.binding.etAddBahan.text.toString().toUpperCase(), this.binding.etAddCara.text.toString(), this.binding.etAddResto.text.toString()))

        this.binding.etAddTitle.setText("")
        this.binding.etAddDesc.setText("")
        this.binding.etAddTag.setText("")
        this.binding.etAddBahan.setText("")
        this.binding.etAddCara.setText("")
        this.binding.etAddResto.setText("")

        this.viewModel.changePage("Menu")
        this.viewModel.closeKey()
    }
}