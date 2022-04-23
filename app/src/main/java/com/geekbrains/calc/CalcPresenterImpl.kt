package com.geekbrains.calc

import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PresenterState(
    var currentUserInput: String = "",
    var pendingOp: CalcModel.Op = CalcModel.Op.ADD,
    var pendingValue: Double = 0.0,
    var pendingValueSet: Boolean = false
) : Parcelable

class CalcPresenterImpl(private val view: CalcView, private val model: CalcModel) : CalcPresenter {
    private var state: PresenterState = PresenterState()

    override fun handleDigit(d: Int) {
        if (state.currentUserInput == "0") {
            state.currentUserInput =
                d.toString() // чтобы не возникало подряд идущих нулей в начале числа
        } else {
            state.currentUserInput += d.toString()
        }
        updateView()
    }

    override fun handleOp(op: CalcModel.Op) {
        computeMaybe()
        state.pendingOp = op
        updateView()
    }

    override fun handleEquals() {
        computeMaybe()
        updateView()
    }

    override fun handleClear() {
        state.currentUserInput = ""
        state.pendingValueSet = false
        updateView()
    }

    override fun handleDot() {
        if (!state.currentUserInput.contains(".")) {
            state.currentUserInput += '.'
        }
        if (state.currentUserInput == ".") {
            state.currentUserInput = "0."
        }
        updateView()
    }

    override fun save(bundle: Bundle) {
        bundle.putParcelable("CalcPresenterImpl", state)
    }

    override fun load(bundle: Bundle) {
        state = bundle.getParcelable("CalcPresenterImpl")!!
    }

    private fun updateView() {
        val text: String = when {
            userEnteredNumber() -> {
                // пользователь начал вводить новое число, показываем что он вводит
                state.currentUserInput
            }
            state.pendingValueSet -> {
                // иначе показываем результат прошлой операции, если он есть
                state.pendingValue.toString().replace("\\.0$".toRegex(), "")
            }
            else -> {
                // иначе был сброс, показываем 0
                "0"
            }
        }
        view.setCalcDisplay(text)
    }

    private fun userEnteredNumber(): Boolean {
        return state.currentUserInput != ""
    }

    private fun computeMaybe() {
        if (!userEnteredNumber())
            return
        val cur = state.currentUserInput.toDouble()
        if (!state.pendingValueSet) {
            state.pendingValue = cur
        } else {
            state.pendingValue = model.binaryOp(state.pendingValue, cur, state.pendingOp)
        }
        state.pendingValueSet = true
        state.currentUserInput = ""
    }

}
