package com.example.myphone.fragments

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.databinding.Bindable
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myphone.ContactAdapter
import com.example.myphone.interfaces.Notify
import com.example.myphone.models.ModelCard
import com.example.myphone.models.ShowDetailItemModel
import com.example.myphone.R
import com.example.myphone.databinding.ContactListBinding
import kotlin.collections.HashSet

class ContactList : Fragment(){
    lateinit var mProvider: ContentResolver
    lateinit var mRecycler: RecyclerView
    lateinit var mAdapter: ContactAdapter
    var mShowDetailItemModel: ShowDetailItemModel? = null
    fun instance(): ContactList {
        return ContactList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var binding:ContactListBinding = DataBindingUtil.inflate(inflater,R.layout.contact_list, container, false)
        mRecycler = binding.contactList
        mRecycler.layoutManager = LinearLayoutManager(this.activity)
        var statusPermissions = activity!!.checkSelfPermission(Manifest.permission.READ_CONTACTS)
        if (statusPermissions != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 12)
        }else{
            reqData()
        }
        mShowDetailItemModel = ShowDetailItemModel(1)
        binding.myModel = mShowDetailItemModel


        return binding.root
    }

    fun reqData(){
        var uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        mProvider = activity!!.contentResolver
        var cursor = mProvider.query(
            uri,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.DATA4,
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI
            ),
            null,
            null,
            null
        )
        var list = HashSet<ModelCard>()
        while (cursor.moveToNext()) {

//            for (cur in cursor.columnNames.iterator())
//            Log.d(
//                "test",
//                "column: " + cur + " value: " +  cursor.getString(cursor.getColumnIndex(cur)))
            list.add(
                ModelCard(
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA4)),
                    Color.BLUE
                )
            )

        }


        mAdapter = ContactAdapter(list, activity!!.baseContext)
        mAdapter.Notufy(object : Notify {
            override fun getItemId(id: Int) {

            }
        })
        mRecycler.adapter = mAdapter
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (perm in permissions.indices) {
            if (grantResults[perm] != PackageManager.PERMISSION_GRANTED){
                shouldShowRequestPermissionRationale(permissions.get(perm))
            } else {
                reqData()
            }

        }
    }

}