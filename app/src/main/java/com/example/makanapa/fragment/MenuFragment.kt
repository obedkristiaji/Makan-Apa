package com.example.makanapa.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.makanapa.FragmentListener
import com.example.makanapa.adapter.MenuListAdapter
import com.example.makanapa.databinding.FragmentMenuBinding
import com.example.makanapa.model.Menu

class MenuFragment : Fragment(), View.OnClickListener {
    private lateinit var fl: FragmentListener
    private var menuList: List<Menu> = ArrayList()

    init {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMenuBinding.inflate(inflater, container, false)

        val menu: ListView = binding.lstMenu as ListView
        this.menuList += Menu("Menu A", "desc", "tag", "bahan", "cara", "resto")
        this.menuList += Menu("Menu B", "desc2", "tag2", "bahan2", "cara2", "resto2")

        val adapter = MenuListAdapter(activity!!, this.fl)
        menu.adapter = adapter
        (menu.adapter as MenuListAdapter).update(menuList)

        binding.fab1.setOnClickListener(this)

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

    override fun onClick(v: View?) {
        this.fl.changePage(5)
    }
}