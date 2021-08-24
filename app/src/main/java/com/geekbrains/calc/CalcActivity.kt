package com.geekbrains.calc

import android.os.Bundle
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity

class CalcActivity : AppCompatActivity(), CalcView {
    val calcModel: CalcModel = CalcModelImpl()
    val calcPresenter: CalcPresenter = CalcPresenterImpl(this, calcModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<GridLayout>(R.id.grid).setBackgroundResource(R.drawable.bg)
    }

    override fun setCalcDisplay(text: String) {
        TODO("Not yet implemented")
    }
}