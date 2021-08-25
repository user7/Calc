package com.geekbrains.calc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalcActivity : AppCompatActivity(), CalcView {
    val calcModel: CalcModel = CalcModelImpl()
    val calcPresenter: CalcPresenter = CalcPresenterImpl(this, calcModel)
    var displayView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)
        findViewById<GridLayout>(R.id.grid).setBackgroundResource(R.drawable.bg)
        displayView = findViewById<TextView>(R.id.calc_display_view)

        fun setListener(buttonId: Int, block: (View) -> Unit)
            = findViewById<Button>(buttonId)!!.setOnClickListener(block)

        setListener(R.id.b0) { calcPresenter.handleDigit(0) }
        setListener(R.id.b1) { calcPresenter.handleDigit(1) }
        setListener(R.id.b2) { calcPresenter.handleDigit(2) }
        setListener(R.id.b3) { calcPresenter.handleDigit(3) }
        setListener(R.id.b4) { calcPresenter.handleDigit(4) }
        setListener(R.id.b5) { calcPresenter.handleDigit(5) }
        setListener(R.id.b6) { calcPresenter.handleDigit(6) }
        setListener(R.id.b7) { calcPresenter.handleDigit(7) }
        setListener(R.id.b8) { calcPresenter.handleDigit(8) }
        setListener(R.id.b9) { calcPresenter.handleDigit(9) }
        setListener(R.id.bdot) { calcPresenter.handleDot() }

        setListener(R.id.badd) { calcPresenter.handleOp(CalcModel.Op.ADD) }
        setListener(R.id.bsub) { calcPresenter.handleOp(CalcModel.Op.SUB) }
        setListener(R.id.bmul) { calcPresenter.handleOp(CalcModel.Op.MUL) }
        setListener(R.id.bdiv) { calcPresenter.handleOp(CalcModel.Op.DIV) }

        setListener(R.id.bclear) { calcPresenter.handleClear() }
        setListener(R.id.bequals) { calcPresenter.handleEquals() }

        calcPresenter.handleEquals() // чтобы дисплей обновился
    }

    override fun setCalcDisplay(text: String) {
        displayView!!.setText(text)
    }
}