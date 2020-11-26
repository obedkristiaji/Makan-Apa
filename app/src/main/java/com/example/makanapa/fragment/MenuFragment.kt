package com.example.makanapa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.makanapa.adapter.MenuListAdapter
import com.example.makanapa.databinding.FragmentMenuBinding
import com.example.makanapa.model.Menu
import com.example.makanapa.viewmodel.MainActivityViewModel

class MenuFragment : Fragment(), View.OnClickListener {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: FragmentMenuBinding

    init {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        val lstMenu: ListView = binding.lstMenu as ListView

        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        val adapter = MenuListAdapter(activity!!, this.viewModel)
        lstMenu.adapter = adapter

        viewModel.search.observe(viewLifecycleOwner, Observer<String> { search ->
            if(search.equals("")){
                viewModel.menuList.observe(viewLifecycleOwner, Observer<List<Menu>> { menuList ->
                    (lstMenu.adapter as MenuListAdapter).update(menuList)
                })
            } else {
                viewModel.menuSearch.observe(viewLifecycleOwner, Observer<List<Menu>> { menuSearch ->
                    (lstMenu.adapter as MenuListAdapter).update(menuSearch)
                })
            }
        })

        viewModel.dark.observe(viewLifecycleOwner, Observer<Boolean> { dark ->
            this.viewModel.darkEdit(dark, this.binding.etSearch)
        })

        binding.fabNew.setOnClickListener(this)
        binding.btnSearch.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(v: View?) {
        if(v == this.binding.fabNew) {
            this.viewModel.changePage("Add")
        } else if(v == this.binding.btnSearch) {
            this.viewModel.resetSearch()
            this.viewModel.searchMenu(this.binding.etSearch.text.toString().toUpperCase())
            this.viewModel.closeKey()
            this.viewModel.changePage("Menu")
        }
    }
}