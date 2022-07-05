package com.example.cs496_1stweek.contact

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_1stweek.R


class ContactFragment : Fragment() {

    private var canCall = false
    @SuppressLint("Recycle")
    fun loadContact() : MutableList<ContactItem> {
        // 전화번호
        val phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        //val phoneUri = ContactsContract.Contacts.CONTENT_URI
        val projections = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI,
        )
        val orderBy = "upper("+ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + ") ASC"
        val contactItemList = mutableListOf<ContactItem>()
        val cursor =
            requireActivity().contentResolver.query(phoneUri, projections, null, null, orderBy)

        while(cursor?.moveToNext() == true) {
            val name = cursor.getString(0)
            Log.d("check", cursor.getString(1))
            val number = cursor.getString(1)
            var pic = cursor.getString(2)
            // 개별 전화번호 데이터 생성
            if(pic==null) pic="null"
            val phone = ContactItem(pic, name, number)

            // 결과목록에 더하기
            contactItemList.add(phone)
        }
        return contactItemList
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contactView = inflater.inflate(R.layout.contact_frag, container, false)
        val recycleView: RecyclerView = contactView.findViewById(R.id.contact_recycler_view)

        val contactItemList = mutableListOf<ContactItem>()
        val adapter = ContactAdapter(contactItemList)

        adapter.onItemClick = {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:"+it.phoneNum)
            startActivity(callIntent)
        }

        val askMultiplePermissionsLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
            for (entry in map.entries) {
                if(entry.key == "android.permission.READ_CONTACTS" && entry.value == true && contactItemList.size == 0) {
                    val numbersIterator = loadContact().iterator()
                    while(numbersIterator.hasNext()) {
                        contactItemList.add(numbersIterator.next())
                    }
                    adapter.notifyDataSetChanged()
                }
                if(entry.key == "android.permission.CALL_PHONE" && entry.value == true) {
                    canCall = true
                }
            }
            if(map.values.contains(false)) Toast.makeText(activity,"앱을 사용하기 위해서 권한을 허용해야합니다.",Toast.LENGTH_SHORT).show();
        }

        askMultiplePermissionsLauncher.launch(arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE))

        recycleView.adapter = adapter
        return contactView
    }
}

