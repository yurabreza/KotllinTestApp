package com.rxboost.kotllintestapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import butterknife.bindView
import com.github.nitrico.lastadapter.BR
import com.github.nitrico.lastadapter.LastAdapter
import com.rxboost.kotllintestapp.R
import com.rxboost.kotllintestapp.model.RetrofitMain
import com.rxboost.kotllintestapp.model.data.Store
import com.rxboost.kotllintestapp.model.data.StoresCallResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    val apiInterface: RetrofitMain = RetrofitMain()

    val mainRecyclerView: RecyclerView by bindView(R.id.rv_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val callResponse = apiInterface.getStores()
        Log.d(TAG, callResponse.toString())

        callResponse.enqueue(object : Callback<StoresCallResult> {
            override fun onResponse(call: Call<StoresCallResult>, response: Response<StoresCallResult>) {
                Log.d(TAG, response.message())
                if (response.isSuccessful) {
                    Log.d(TAG, response.body().toString())
                    Log.d(TAG, "record count" + response.body().pager!!.totalRecordCount.toString())
                    Log.d(TAG, "result size" + response.body().result!!.size.toString())
                    var list: ArrayList<Store> = ArrayList()
                    mainRecyclerView.layoutManager = LinearLayoutManager(baseContext)
                    for (store in response.body().result!!) list.add(store)
                    LastAdapter.with(list, BR.item)
                            .map<Store>(R.layout.item_store)
                            .into(mainRecyclerView)

                }
            }

            override fun onFailure(call: Call<StoresCallResult>, t: Throwable) {
                t.printStackTrace()
            }
        })


    }
}

