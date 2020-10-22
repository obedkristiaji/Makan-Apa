package com.example.makanapa.model

class Menu {
    private var title: String
    private var desc: String
    private var tag: String
    private var bahan: String
    private var cara: String
    private var resto: String

    constructor(title: String, desc: String, tag: String, bahan: String, cara: String, resto: String) {
        this.title = title
        this.desc = desc
        this.tag = tag
        this.bahan = bahan
        this.cara = cara
        this.resto = resto
    }

    fun getTitle(): String {
        return this.title
    }

    fun getDesc(): String {
        return this.desc
    }

    fun getTag(): String {
        return this.tag
    }

    fun getBahan(): String {
        return this.bahan
    }

    fun getCara(): String {
        return this.cara
    }

    fun getResto(): String {
        return this.resto
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun setDesc(desc: String) {
        this.desc = desc
    }

    fun setTag(tag: String) {
        this.tag = tag
    }

    fun setBahan(bahan: String) {
        this.bahan = bahan
    }

    fun setCara(cara: String) {
        this.cara = cara
    }

    fun setResto(resto: String) {
        this.resto = resto
    }
}