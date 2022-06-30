package com.example.cs496_1stweek.contact

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContentResolver
import android.media.Image
import android.os.Build
import com.example.cs496_1stweek.R
import android.provider.ContactsContract
import android.util.Log


class ContactItem() {
    @SuppressLint("RestrictedApi")
    fun loadRecycleview(): List<RecycleView> {
        /*val contactItemList = listOf<RecycleView>()
        // 주소록
        val addressUri = ContactsContract.Contacts.CONTENT_URI
        // 전화번호
        val phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        // 표시할 정보
        val projections = arrayOf(
            ContactsContract.CommonDataKinds.Photo.PHOTO_FILE_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )
        /*
        val cursor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requireActivity().contentResolver.query(phoneUri, projections, null, null)
        } else {
            TODO("VERSION.SDK_INT < O")
        }

*/
        Log.d("hello", "hello")*/
        return listOf<RecycleView>(
            RecycleView(0,"aaa","111"),
            RecycleView(0,"bbb","222"),
            RecycleView(0,"ccc","333"),
            RecycleView(0,"ddd","444"),
            RecycleView(0,"eee","555"),
            RecycleView(0,"fff","666")
        )

        //return contactItemList
    }
}

data class RecycleView (var pic: Int, var name: String, var phoneNum: String)