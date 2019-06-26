package com.example.myphone.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myphone.R

class BlackWhiteList:Fragment() {
    lateinit var recycler: RecyclerView

    fun instance(): BlackWhiteList {
        return BlackWhiteList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.black_white_list,container,false)
        recycler = view.findViewById(R.id.black_white_list)
        return view
    }
}