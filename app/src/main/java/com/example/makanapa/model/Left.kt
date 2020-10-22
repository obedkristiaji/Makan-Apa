package com.example.makanapa.model

class Left(title: String, id: Int) {
    private var id: Int
    private lateinit var title: String

    init {
        this.id = id
        this.title = title
    }

    fun getId(): Int {
        return this.id
    }

    fun getTitle(): String {
        return this.title
    }
}