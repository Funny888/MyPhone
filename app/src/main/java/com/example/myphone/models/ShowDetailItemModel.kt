package com.example.myphone.models

import android.databinding.BaseObservable
import android.databinding.Bindable



class ShowDetailItemModel(var _id:Int): BaseObservable() {
   var mId:Int
   @Bindable get() = _id
    set(value) {
        _id = value
    }
}