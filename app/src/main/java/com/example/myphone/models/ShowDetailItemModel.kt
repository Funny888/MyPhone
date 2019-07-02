package com.example.myphone.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.myphone.BR
import com.example.myphone.interfaces.Notify


class ShowDetailItemModel : BaseObservable() {
    lateinit var noty: Notify
    private var mId: Int = 0
    private var isShow: Boolean = false
    private var mIsMaxFrag = false

    @Bindable
    fun getMid(): Int {
        return mId
    }

    fun setMid(_id: Int) {
        mId = _id
        notifyPropertyChanged(BR.mid)
    }

    @Bindable
    fun getIsShow(): Boolean {
        return isShow
    }

    fun setIsShow(_isShow: Boolean) {
        isShow = _isShow
        notifyPropertyChanged(BR.isShow)
    }

    fun notify(_noty: Notify): Notify {
        noty = _noty
        return noty
    }


    fun closeShowItem() {
        noty.getItemId(mId, false)
    }

    @Bindable
    fun getMaxFrag(): Boolean {
        return mIsMaxFrag
    }

    fun setMaxFrag(_max: Int) {
        if (_max != 0)
            mIsMaxFrag = _max > 100
        else
            closeShowItem()
        notifyPropertyChanged(BR.maxFrag)
    }
}