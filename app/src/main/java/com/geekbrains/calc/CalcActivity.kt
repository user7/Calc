package com.geekbrains.calc

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.NumberFormat

class CalcActivity : AppCompatActivity(), CalcView {
    private val calcModel: CalcModel = CalcModelImpl()
    private val calcPresenter: CalcPresenter = CalcPresenterImpl(this, calcModel)
    private var displayView: TextView? = null
    private var sep: Char = '.'
    private val themes: List<Int> = listOf(R.style.Theme_Calc, R.style.ThemeCalcPink)
    private var currentTheme = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let { calcPresenter.load(it) }
        rebuildUI()
    }

    private fun rebuildUI() {
        setTheme(themes[currentTheme])
        setContentView(R.layout.activity_calc)
        findViewById<GridLayout>(R.id.grid).setBackgroundResource(R.drawable.bg)
        displayView = findViewById<TextView>(R.id.calc_display_view)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val df = NumberFormat.getInstance() as? DecimalFormat
            df?.let { sep = it.decimalFormatSymbols.decimalSeparator }
            findViewById<Button>(R.id.bdot).text = "$sep"
        }

        fun setListener(buttonId: Int, block: (View) -> Unit) =
            findViewById<Button>(buttonId)!!.setOnClickListener(block)

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

        setListener(R.id.btheme) {
            currentTheme = (currentTheme + 1) % themes.size
            rebuildUI()
            calcPresenter.updateView();
        }
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        super.onSaveInstanceState(bundle)
        calcPresenter.save(bundle)
    }

    override fun setCalcDisplay(text: String) {
        // отображаем локальный сепаратор вместо точки
        displayView?.text = text.replace('.', sep)
    }

}