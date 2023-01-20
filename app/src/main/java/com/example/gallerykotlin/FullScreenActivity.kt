package com.example.gallerykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class FullScreenActivity : AppCompatActivity() {
    private lateinit var mySharedPref: MySharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)

        mySharedPref= MySharedPref(this)
        val imageSavedInSharedPref= mySharedPref.getString("imageUrl")
        Log.e("shared pref","value "+imageSavedInSharedPref)
    }
}