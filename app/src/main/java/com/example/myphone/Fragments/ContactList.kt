package com.example.myphone.Fragments

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.UserDictionary
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.myphone.ContactAdapter
import com.example.myphone.Interfaces.Notify
import com.example.myphone.R
import java.util.ArrayList
import kotlin.math.log

class ContactList :Fragment(){
    lateinit var provider:ContentResolver
    lateinit var noty:Notify
    lateinit var recycler:RecyclerView
    lateinit var adapter:ContactAdapter
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is Notify) {
            noty = context
        }
    }

    fun instance(): ContactList {
        return ContactList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.contact_list,container,false)
        recycler = view.findViewById(R.id.contact_list)
        recycler.layoutManager = LinearLayoutManager(this.activity)
        recycler.setHasFixedSize(false)
        var uri:Uri = ContactsContract.Data.CONTENT_URI
        provider = activity!!.contentResolver
        var cursor = provider.query(uri, arrayOf( ContactsContract.Data.DATA1),null,null,null)
        while (cursor.moveToNext()){
            for (i in cursor.columnNames.iterator())
                Log.d("test","column: " + i +" value: " + cursor.getString(cursor.getColumnIndex(i)))
        }
        var list = arrayListOf<String>()
        list.add("foo")
        adapter = ContactAdapter(list, activity!!.baseContext)
        recycler.adapter = adapter

        return view
    }
}