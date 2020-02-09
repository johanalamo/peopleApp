package com.example.johan.person.viewmodel


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.johan.person.ConfigApp
import com.example.johan.person.response.MapPerson
import com.example.johan.person.response.RemoteDataResponse
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException

class PersonListViewModel : ViewModel() {
    private val phoneBookList = MutableLiveData<MapPerson>()

    private val TAG = PersonListViewModel::class.java.simpleName

    fun loadPersonListData() {
        val client = OkHttpClient()
        val request = Request.Builder().url(ConfigApp.getUrlPersonList()).build()

        client.newCall(request).enqueue(
            object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    var msg: String = ""
                    if (e::class == UnknownHostException::class) {
                        msg = "UnknownHostException:"
                    }
                    if (e::class == ConnectException::class) {
                        msg = "ConnectException"
                    }
                    Log.d(TAG, "onFailure")
                    phoneBookList.postValue(null)
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d(this::class.java.simpleName, " -------   onResponse")
                    var r = response.body()?.string()
                    var gson = Gson()
                    var data = gson.fromJson(r, RemoteDataResponse::class.java)
                    var results = data.results
                    val map = results?.associateBy({ it.login?.uuid }, { it })
                    try {
                        phoneBookList.postValue(map)
                        Log.d(TAG, "onResponse: size -> " + map?.size)

                    } catch (e: Exception) {
                        Log.d(
                            this::class.java.simpleName,
                            " -------   onResponse inside catch exception",
                            e
                        )
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
