package com.example.myphone.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myphone.R

class ContactList :Fragment(){
    lateinit var recycler:RecyclerView

    fun instance(): ContactList {
        return ContactList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.contact_list,container,false)
        recycler = view.findViewById(R.id.contact_list)
        return view
    }
}