package com.example.cs496_1stweek.contact

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_1stweek.R

class ContactFragment : Fragment() {
    @SuppressLint("Recycle")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contact_view = inflater.inflate(R.layout.contact_frag, container, false)
        val recycleView: RecyclerView = contact_view.findViewById(R.id.contact_recycler_view)
        val myDataset = ContactItem().loadRecycleview()

        val contactItemList = mutableListOf<RecycleView>()
        // 주소록
        val addressUri = ContactsContract.Contacts.CONTENT_URI
        // 전화번호
        val phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        // 표시할 정보
        val projections = arrayOf(
            //ContactsContract.CommonDataKinds.Photo.PHOTO_FILE_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    val cursor =
                        requireActivity().contentResolver.query(addressUri, projections, null, null)
                    while(cursor?.moveToNext() == true) {
                        Log.d("hello","counting")
                        val id = cursor.getString(0).toInt()
                        val name = cursor.getString(1)
                        val number = cursor.getString(2)
                        // 개별 전화번호 데이터 생성
                        val phone = RecycleView(id, name, number)

                        // 결과목록에 더하기
                        contactItemList.add(phone)
                    }
                } else {
                    Toast.makeText(getActivity(),"앱을 사용하기 위해서 권한을 허용해야합니다.",Toast.LENGTH_SHORT).show();

                }
            }

        when{
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED -> {
                Log.d("debuggigngign", "I got permission")
                Log.d("debuggigngign", addressUri.toString())
                Log.d("debuggigngign", projections[0])
                Log.d("debuggigngign", projections[1])
                //Log.d("debuggigngign", projections[2])
                val cursor =
                    requireActivity().contentResolver.query(phoneUri, projections, null, null, null)
                Log.d("debuggigngign", "I got permission2")
                while(cursor?.moveToNext() == true) {
                    //val id = cursor.getString(0).toInt()
                    val name = cursor.getString(0)//val name = cursor.getString(1)
                    val number = cursor.getString(1)//val number = cursor.getString(2)
                    // 개별 전화번호 데이터 생성
                    val phone = RecycleView(0,name, number)//val phone = RecycleView(id, name, number)

                    // 결과목록에 더하기
                    contactItemList.add(phone)
                }
                Log.d("debuggigngign", "I got permission3")
            }
            else->{
                Toast.makeText(getActivity(),"앱을 사용하기 위해서 권한을 허용해야합니다.",Toast.LENGTH_SHORT).show();
                requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
            }
        }

        /*val cursor =  requireActivity().contentResolver.query(addressUri, projections, null, null )

        while(cursor?.moveToNext() == true) {
            val id = cursor.getString(0).toInt()
            val name = cursor.getString(1)
            val number = cursor.getString(2)
            // 개별 전화번호 데이터 생성
            val phone = RecycleView(id, name, number)

            // 결과목록에 더하기
            contactItemList.add(phone)
        }*/
        Log.d("hello",contactItemList.size.toString())

        recycleView.adapter = ContactAdapter(this, contactItemList)
        return contact_view
}
}

