package com.geekbrains.calc

interface CalcPresenter {
    fun handleDigit(d: Int)
    fun handleOp(op: CalcModel.Op)
    fun handleEquals()
    fun handleClear()
    fun handleDot()
}