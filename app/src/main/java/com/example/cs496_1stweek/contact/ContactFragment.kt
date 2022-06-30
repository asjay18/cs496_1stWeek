package com.example.cs496_1stweek.contact

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_1stweek.R

class ContactFragment : Fragment() {
    @SuppressLint("Recycle")
    fun loadcontact() : MutableList<RecycleView> {
        // 전화번호
        val phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        //val phoneUri = ContactsContract.Contacts.CONTENT_URI
        val projections = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI,
        )
        val contactItemList = mutableListOf<RecycleView>()
        val cursor =
            requireActivity().contentResolver.query(phoneUri, projections, null, null, null)

        while(cursor?.moveToNext() == true) {
            val name = cursor.getString(0)
            val number = cursor.getString(1)
            var pic = cursor.getString(2)
            // 개별 전화번호 데이터 생성
            if(pic==null) pic="null"
            val phone = RecycleView(pic, name, number)//val phone = RecycleView(id, name, number)

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
        val contact_view = inflater.inflate(R.layout.contact_frag, container, false)
        val recycleView: RecyclerView = contact_view.findViewById(R.id.contact_recycler_view)

        val contactItemList = mutableListOf<RecycleView>()
        val adapter = ContactAdapter(this, contactItemList)

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    val numbersIterator = loadcontact().iterator()
                    while(numbersIterator.hasNext()) {
                        contactItemList.add(numbersIterator.next())
                    }
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(getActivity(),"앱을 사용하기 위해서 권한을 허용해야합니다.",Toast.LENGTH_SHORT).show();
                }
            }

        when{
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED -> {
                val numbersIterator = loadcontact().iterator()
                while(numbersIterator.hasNext()) {
                    contactItemList.add(numbersIterator.next())
                }
            }
            else->{
                Toast.makeText(getActivity(),"앱을 사용하기 위해서 권한을 허용해야합니다.",Toast.LENGTH_SHORT).show();
                requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
            }
        }

        recycleView.adapter = adapter
        return contact_view
    }
}

