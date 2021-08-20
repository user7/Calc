package com.geekbrains.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<GridLayout>(R.id.grid).setBackgroundResource(R.drawable.bg)
    }
}