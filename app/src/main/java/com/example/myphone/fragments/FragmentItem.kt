package com.example.myphone.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.myphone.R

class FragmentItem: Fragment() {
    private var mName:String = ""
    var mPhone:String = ""
    var mPhoto:String = ""

    fun getInstance():FragmentItem{
        return FragmentItem()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View = inflater.inflate(R.layout.item_list,container,false)
         var Name =   view.findViewById<TextView>(R.id.display_name)
         var Phone =   view.findViewById<TextView>(R.id.phone)
         var Photo =   view.findViewById<ImageView>(R.id.photo)
        Name.setText(mName)
        Phone.setText(mPhone)
        return view
    }

    fun setData(_Name:String,_Phone:String,_Photo:String){
        mName =_Name
        mPhone =_Phone
        mPhoto =_Photo
    }
}