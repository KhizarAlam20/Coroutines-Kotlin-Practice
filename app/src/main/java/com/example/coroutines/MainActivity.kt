package com.example.coroutines

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.contextaware.withContextAvailable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var img: ImageView
    lateinit var btn: Button
    lateinit var count: Button
    lateinit var tvCount: TextView
    var counter: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        img = findViewById(R.id.img)
        btn = findViewById(R.id.btn)
        count = findViewById(R.id.count)
        tvCount = findViewById(R.id.tvCount)

        btn.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {

                try {
                    var URLs = URL("https://imgur.com/xzVxzEo.jpg")
                    var bitmap = BitmapFactory.decodeStream(URLs.openStream())

                    withContext(Dispatchers.Main) {
                        img.setImageBitmap(bitmap)
                    }

                } catch (e: Exception) {
                    Log.d("COROUTINE_TAG", "Coroutine : ${e}")
                }
            }
        }



        count.setOnClickListener {
            counter += 1

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    withContext(Dispatchers.Main) {
                        tvCount.text = counter.toString()
                    }
                } catch (e: Exception) {
                    Log.d("myTag", "Coroutine Counter : ${e}")
                }
            }
        }

    }
}