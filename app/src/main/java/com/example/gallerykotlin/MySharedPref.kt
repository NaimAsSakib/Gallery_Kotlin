package com.example.gallerykotlin

import android.content.Context
import android.content.SharedPreferences

class MySharedPref( getContext: Context){
    //This class is taken only for shared preferences purpose
    //This class is taken only for shared preferences purpose
    // through this class I can us it's methods by making its object anywhere in the project
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    init {
        sharedPreferences = getContext.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        editor = sharedPreferences!!.edit()
    }
    fun putInt(key: String?, value: Int) {
        editor!!.putInt(key, value)
        editor!!.apply()
    }

    fun putBoolean(key: String?, value: Boolean) {
        editor!!.putBoolean(key, value)
        editor!!.apply()
    }

    fun putString(key: String?, value: String?) {
        editor!!.putString(key, value)
        editor!!.apply()
    }

    fun putFloat(key: String?, value: Float) {
        editor!!.putFloat(key, value)
        editor!!.apply()
    }

    fun putDouble(key: String?, value: Double) {
        editor!!.putFloat(key, value.toFloat())
        editor!!.apply()
    }

    fun putLong(key: String?, value: Long?) {
        editor!!.putLong(key, value!!)
        editor!!.apply()
    }

    fun getInt(key: String?): Int? {
        return sharedPreferences!!.getInt(key, 0) //setting default value
    }

    fun getBoolean(key: String?): Boolean? {
        return sharedPreferences!!.getBoolean(key, false)
    }

    fun getString(key: String?): String? {
        return sharedPreferences!!.getString(key, "")
    }

    fun getDouble(key: String?): Float {
        return sharedPreferences!!.getFloat(key, 0f)
    }

    fun getLong(key: String?): Long? {
        return sharedPreferences!!.getLong(key, 0)
    }

    fun clearData() {
        editor!!.clear()
        editor!!.apply()
    }

}