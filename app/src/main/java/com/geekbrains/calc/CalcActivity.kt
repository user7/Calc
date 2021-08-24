package com.geekbrains.calc

import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalcActivity : AppCompatActivity(), CalcView {
    val calcModel: CalcModel = CalcModelImpl()
    val calcPresenter: CalcPresenter = CalcPresenterImpl(this, calcModel)
    var displayView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<GridLayout>(R.id.grid).setBackgroundResource(R.drawable.bg)
        displayView = findViewById<TextView>(R.id.calc_display_view)
    }

    override fun setCalcDisplay(text: String) {
        displayView!!.setText(text)
    }
}