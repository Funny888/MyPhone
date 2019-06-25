package com.example.myphone.Fragments

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myphone.ContactAdapter
import com.example.myphone.Interfaces.Notify
import com.example.myphone.Models.ModelCard
import com.example.myphone.R

class ContactList : Fragment() {
    lateinit var provider: ContentResolver
    lateinit var noty: Notify
    lateinit var recycler: RecyclerView
    lateinit var adapter: ContactAdapter
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Notify) {
            noty = context
        }
    }

    fun instance(): ContactList {
        return ContactList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.contact_list, container, false)
        recycler = view.findViewById(R.id.contact_list)
        recycler.layoutManager = LinearLayoutManager(this.activity)
        recycler.setHasFixedSize(false)
        var statusPermissions = activity!!.checkSelfPermission(Manifest.permission.READ_CONTACTS)
        if (statusPermissions != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 12)
        }else{
            reqData()
        }


        return view
    }

    fun reqData(){
        var uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        provider = activity!!.contentResolver
        var cursor = provider.query(
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


        adapter = ContactAdapter(list, activity!!.baseContext)
        recycler.adapter = adapter
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