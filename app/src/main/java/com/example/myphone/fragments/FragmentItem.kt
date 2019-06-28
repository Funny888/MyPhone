package com.example.myphone.fragments

import android.net.Uri
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
        var view:View = inflater.inflate(R.layout.profile_item,container,false)
         var Name =   view.findViewById<TextView>(R.id.name_profile)
         var Phone =   view.findViewById<TextView>(R.id.phone_profile)
         var Photo =   view.findViewById<ImageView>(R.id.photo_profile)
        Name.setText(getString(R.string.str_name) + " " + mName)
        Phone.setText(getString(R.string.str_phone) + " " + mPhone)
        if (!mPhoto.equals("null")){
            Photo.setImageURI(Uri.parse(mPhoto))
        }else{
            var sex:String = mName.subSequence(mName.length-1,mName.length).toString()
            if (sex.equals("а") || sex.equals("А") || sex.equals("я") || sex.equals("Я") ||sex.equals("a") || sex.equals("A") ) {
                Photo.setImageResource(R.mipmap.profile_female)
            } else {
                Photo.setImageResource(R.mipmap.profile_male)
            }

        }
        return view
    }

    fun setData(_Name:String,_Phone:String,_Photo:String){
        mName =_Name
        mPhone =_Phone
        mPhoto =_Photo
    }
}