package com.geekbrains.calc

import android.os.Bundle

interface CalcPresenter {
    fun handleDigit(d: Int)
    fun handleOp(op: CalcModel.Op)
    fun handleEquals()
    fun handleClear()
    fun handleDot()
    fun save(bundle: Bundle)
    fun load(bundle: Bundle)
}