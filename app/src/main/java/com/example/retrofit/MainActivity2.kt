package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.text.StringBuilder

const val url = "https://type.fit/"
private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getQuotes()
    }
        private fun getQuotes() {
            val retrofit =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(url)
                    .build().create(APIInterface::class.java)
            val data = retrofit.getQuote()
            data.enqueue(object : Callback<List<Data>?> {
                override fun onResponse(call: Call<List<Data>?>, response: Response<List<Data>?>) {
                    val body = response.body()!!
                    val stringbuilder = StringBuilder()
                    var count = 1
                    for (data in body) {
                        stringbuilder.append("$count"+")"+data.text)
                        stringbuilder.append("\n")
                        count+=1
                    }
                    binding.txtId.text = stringbuilder
                }

                override fun onFailure(call: Call<List<Data>?>, t: Throwable) {
                    Log.d("MainActivity", "onfailure" + t.message)
                }
            })
        }
    }
