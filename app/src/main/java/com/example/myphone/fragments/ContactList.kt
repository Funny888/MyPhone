package com.example.myphone.fragments

import android.Manifest
import android.content.*
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myphone.ContactAdapter
import com.example.myphone.R
import com.example.myphone.databinding.ContactListBinding
import com.example.myphone.interfaces.Notify
import com.example.myphone.models.ModelCard
import com.example.myphone.models.ShowDetailItemModel

class ContactList : Fragment(), Notify {
    lateinit var mProvider: ContentResolver
    lateinit var mRecycler: RecyclerView
    lateinit var mAdapter: ContactAdapter
    lateinit var mList: HashSet<ModelCard>
    lateinit var mProfile: FragmentItem
    lateinit var ctx: Context
    var br: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent!!.action.equals(getString(R.string.intent_filter))) {
                mShowDetailItemModel!!.setMaxFrag(intent.getIntExtra("max", 0))
            }
        }
    }
    var mShowDetailItemModel: ShowDetailItemModel? = null
    fun instance(): ContactList {
        return ContactList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var binding: ContactListBinding = DataBindingUtil.inflate(inflater, R.layout.contact_list, container, false)
        mRecycler = binding.contactList
        mRecycler.layoutManager = LinearLayoutManager(this.activity)
        var statusPermissions = activity!!.checkSelfPermission(Manifest.permission.READ_CONTACTS)
        if (statusPermissions != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 12)
        } else {
            reqData()
        }
        mShowDetailItemModel = ShowDetailItemModel()
        mShowDetailItemModel!!.notify(this)
        binding.myModel = mShowDetailItemModel
        ctx = binding.root.context
        ctx.registerReceiver(br, IntentFilter(getString(R.string.intent_filter)))
        return binding.root
    }

    fun reqData() {
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

            for (cur in cursor.columnNames.iterator()) {
                Log.d(
                    "closeShowItem",
                    "column: " + cur + " value: " + cursor.getString(cursor.getColumnIndex(cur))
                )
                list.add(
                    ModelCard(
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA4)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
                            ?: "null"
                    )
                )
            }
        }


        mList = list
        mAdapter = ContactAdapter(mList, activity!!.baseContext)
        mAdapter.Notufy(this)
        mRecycler.adapter = mAdapter
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (perm in permissions.indices) {
            if (grantResults[perm] != PackageManager.PERMISSION_GRANTED) {
                shouldShowRequestPermissionRationale(permissions.get(perm))
            } else {
                reqData()
            }

        }
    }

    override fun getItemId(id: Int, isShow: Boolean) {
        mShowDetailItemModel?.setMid(id)
        mShowDetailItemModel?.setIsShow(isShow)
        mProfile = FragmentItem().getInstance()
        mProfile.setData(mList.elementAt(id).name, mList.elementAt(id).phone, mList.elementAt(id).photo)
        var frag: FragmentTransaction = childFragmentManager.beginTransaction().replace(R.id.frame_item, mProfile)
        frag.commit()
    }

}


