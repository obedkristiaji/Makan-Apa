package com.example.makanapa.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.makanapa.databinding.FragmentSettingBinding
import com.example.makanapa.viewmodel.MainActivityViewModel

class SettingFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var builder: AlertDialog.Builder

    init {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View
        view = FragmentSettingBinding.inflate(inflater, container, false).root
        this.binding = FragmentSettingBinding.bind(view)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        viewModel.dark.observe(viewLifecycleOwner, Observer<Boolean> { dark ->
            this.viewModel.darkText(dark, this.binding.tvClear)
            this.viewModel.darkText(dark, this.binding.tvTheme)
            this.viewModel.darkBtn(dark, this.binding.btnDark)
        })

        this.binding.btnClear.setOnClickListener(this)
        this.binding.btnDark.setOnClickListener(this)


        this.builder = AlertDialog.Builder(activity)
        return view
    }

    override fun onClick(v: View?) {
        if(v == this.binding.btnClear) {
            this.builder.setTitle("Apakah kamu yakin ingin menghapus semua data?")
            this.builder.setPositiveButton("Yes") { dialog, which ->
                this.viewModel.clearData()
                Toast.makeText(this.activity, "Berhasil dihapus", Toast.LENGTH_SHORT).show()
            }

            this.builder.setNegativeButton("No") { dialog, which ->
                Toast.makeText(this.activity, "Gagal dihapus", Toast.LENGTH_SHORT).show()
            }

            this.builder.show()
        } else if (v == this.binding.btnDark) {
            this.viewModel.darkMode()
        }
    }
}