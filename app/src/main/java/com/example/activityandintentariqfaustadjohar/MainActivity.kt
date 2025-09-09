package com.example.activityandintentariqfaustadjohar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.activityandintentariqfaustadjohar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivityLifecycle" // Changed TAG to tag
    private lateinit var binding: ActivityMainBinding
    companion object{
        const val EXTRA_NAME = "extra_name" // This is unused, consider removing if not needed
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            btnToSecondActivity.setOnClickListener {
                val intentToSecondActivity =
                    Intent(this@MainActivity, SecondActivity::class.java)
                startActivity(intentToSecondActivity)
            }
            btnSendMessage.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "Hello, this is a message from my app!")
                startActivity(Intent.createChooser(intent, "Select an app"))
            }
            btnDial.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:1234567890")
                startActivity(intent)
            }
            btnOpenLink.setOnClickListener {
            }
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.google.com")
            startActivity(intent)
        }
        Log.d(tag, "onCreate: dipanggil")
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart: dipanggil")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume: dipanggil")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause: dipanggil")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop: dipanggil")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy: dipanggil")

    }
}