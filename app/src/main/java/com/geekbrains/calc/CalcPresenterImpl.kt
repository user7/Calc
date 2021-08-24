package com.geekbrains.calc

class CalcPresenterImpl(val view: CalcView, val model: CalcModel) : CalcPresenter {
    var currentUserInput: String = ""
    var pendingOp: CalcModel.Op = CalcModel.Op.ADD
    var pendingValue: Double = 0.0
    var pendingValueSet: Boolean = false

    override fun handleDigit(d: Int) {
        if (currentUserInput == "0")
            currentUserInput = d.toString() // чтобы не возникало подряд идущих нулей в начале числа
        else
            currentUserInput += d.toString()
        updateView()
    }

    override fun handleOp(op: CalcModel.Op) {
        computeMaybe()
        pendingOp = op
        updateView()
    }

    override fun handleEquals() {
        computeMaybe()
        updateView()
    }

    override fun handleClear() {
        currentUserInput = ""
        pendingValueSet = false
        updateView()
    }

    override fun handleDot() {
        if (!currentUserInput.contains("."))
            currentUserInput += '.'
        if (currentUserInput == ".")
            currentUserInput = "0."
        updateView()
    }

    private fun updateView() {
        if (!userEnteredNumber() && pendingValueSet) // пока не введено новое число, показываем результат прошлой операции
            view.setCalcDisplay(pendingValue.toString().replace("\\.0$".toRegex(), ""))
        else
            view.setCalcDisplay(if (userEnteredNumber()) currentUserInput else "0")
    }

    private fun userEnteredNumber(): Boolean {
        return currentUserInput != ""
    }

    private fun computeMaybe() {
        if (!userEnteredNumber())
            return
        val cur = currentUserInput.toDouble()
        if (!pendingValueSet) {
            pendingValue = cur
        } else {
            pendingValue = model.binaryOp(pendingValue, cur, pendingOp)
        }
        pendingValueSet = true
        currentUserInput = ""
    }
}