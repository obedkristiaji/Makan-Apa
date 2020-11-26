package com.example.makanapa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.makanapa.databinding.FragmentEditBinding
import com.example.makanapa.model.Menu
import com.example.makanapa.viewmodel.MainActivityViewModel

class EditFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentEditBinding
    private lateinit var viewModel: MainActivityViewModel
    private var position: Int = 0
    private lateinit var search: String

    init {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View
        view = FragmentEditBinding.inflate(inflater, container, false).root
        this.binding = FragmentEditBinding.bind(view)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        viewModel.title.observe(viewLifecycleOwner, Observer<String> { title ->
            this.binding.tvTitle.text = title
        })
        viewModel.desc.observe(viewLifecycleOwner, Observer<String> { desc ->
            this.binding.etEditTitle.setText("")
        })
        viewModel.desc.observe(viewLifecycleOwner, Observer<String> { desc ->
            this.binding.etEditDesc.setText(desc)
        })
        viewModel.tag.observe(viewLifecycleOwner, Observer<String> { tag ->
            this.binding.etEditTag.setText(tag)
        })
        viewModel.bahan.observe(viewLifecycleOwner, Observer<String> { bahan ->
            this.binding.etEditBahan.setText(bahan)
        })
        viewModel.cara.observe(viewLifecycleOwner, Observer<String> { cara ->
            this.binding.etEditCara.setText(cara)
        })
        viewModel.resto.observe(viewLifecycleOwner, Observer<String> { resto ->
            this.binding.etEditResto.setText(resto)
        })

        viewModel.dark.observe(viewLifecycleOwner, Observer<Boolean> { dark ->
            this.viewModel.darkText(dark, this.binding.tvTitle)
            this.viewModel.darkText(dark, this.binding.tvEditTitle)
            this.viewModel.darkText(dark, this.binding.tvEditDesc)
            this.viewModel.darkText(dark, this.binding.tvEditTag)
            this.viewModel.darkText(dark, this.binding.tvEditBahan)
            this.viewModel.darkText(dark, this.binding.tvEditCara)
            this.viewModel.darkText(dark, this.binding.tvEditResto)

            this.viewModel.darkEdit(dark, this.binding.etEditTitle)
            this.viewModel.darkEdit(dark, this.binding.etEditDesc)
            this.viewModel.darkEdit(dark, this.binding.etEditTag)
            this.viewModel.darkEdit(dark, this.binding.etEditBahan)
            this.viewModel.darkEdit(dark, this.binding.etEditCara)
            this.viewModel.darkEdit(dark, this.binding.etEditResto)
        })

        viewModel.position.observe(viewLifecycleOwner, Observer<Int> { position ->
            viewModel.search.observe(viewLifecycleOwner, Observer<String> { search ->
                this.position = position
                this.search = search
            })
        })

        this.binding.fabEditSave.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        if(search.equals("")){
            this.viewModel.editMenu(viewModel.menuList.value!!, position, this.binding.etEditTitle.text.toString().toUpperCase(), this.binding.etEditDesc.text.toString(), this.binding.etEditTag.text.toString().toUpperCase(), this.binding.etEditBahan.text.toString().toUpperCase(), this.binding.etEditCara.text.toString(), this.binding.etEditResto.text.toString())
        } else {
            this.viewModel.editMenu(viewModel.menuSearch.value!!, position, this.binding.etEditTitle.text.toString().toUpperCase(), this.binding.etEditDesc.text.toString(), this.binding.etEditTag.text.toString().toUpperCase(), this.binding.etEditBahan.text.toString().toUpperCase(), this.binding.etEditCara.text.toString(), this.binding.etEditResto.text.toString())
        }

        this.viewModel.changePage("Menu")
        this.viewModel.closeKey()
    }
}