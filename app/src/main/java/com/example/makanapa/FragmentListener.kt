package com.example.makanapa

interface FragmentListener {
    fun changePage(page: Int)
    fun closeApplication()
    fun onMenuListClick(position: Int, title: String, desc: String, tag: String, bahan: String, cara: String, resto:String)
}