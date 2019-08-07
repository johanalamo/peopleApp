package com.example.johan.person.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import okhttp3.*
import java.io.IOException

import java.net.ConnectException
import java.net.UnknownHostException


import com.example.johan.person.ConfigApp
import com.example.johan.person.response.*
import com.google.gson.Gson

class PersonListViewModel : ViewModel() {
   private val phoneBookList = MutableLiveData<MapPerson>()

    fun loadPersonListData() {
      val client = OkHttpClient()
      val request = Request.Builder().url(ConfigApp.getUrlPersonList()).build()

      client.newCall(request).enqueue( object : Callback {
          override fun onFailure(call: Call, e: IOException) {
            var msg: String = ""
            if (e::class == UnknownHostException::class)
                msg = "UnknownHostException:"
            if (e::class == ConnectException::class)
                msg = "ConnectException"
            println( "\n\n\n ************************ $msg  \n\n" + e.toString())

          }

          override fun onResponse(call: Call, response: Response) {
            var r = response.body()?.string()

            var gson = Gson()
            var data = gson.fromJson(r, RemoteDataResponse::class.java)
            var results = data.results
            val map = results?.associateBy({it.login?.uuid}, {it})
            try{
              phoneBookList.postValue(map)
            }catch(e:Exception){
              println("===============error, exception catched=====================")
              println(e)
            }
          }
      }
      )
    }
    fun getPersonList(): LiveData<MapPerson> {
        return phoneBookList
    }
}
