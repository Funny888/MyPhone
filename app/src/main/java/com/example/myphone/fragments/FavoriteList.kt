package com.example.myphone.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myphone.interfaces.Notify
import com.example.myphone.R

class FavoriteList:Fragment() {
    lateinit var noty:Notify
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Notify)
            noty = context

    }

    lateinit var recycler: RecyclerView

    fun instance(): FavoriteList {
        return FavoriteList()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.favorite_list,container,false)
        recycler = view.findViewById(R.id.favorite_list)
        return view
    }
}