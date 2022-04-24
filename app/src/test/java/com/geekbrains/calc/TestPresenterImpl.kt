package com.geekbrains.calc

import android.os.Bundle
import org.junit.Test
import org.mockito.kotlin.*

class TestPresenterImpl {

    @Test
    fun `handling 0 0 2 3`() {
        val view = mock<CalcView>()
        val model = mock<CalcModel>()
        val presenter = CalcPresenterImpl(view, model)

        presenter.handleDigit(0)
        verify(view).setCalcDisplay("0")

        presenter.handleDigit(0)
        verify(view, times(2)).setCalcDisplay("0")

        presenter.handleDigit(2)
        verify(view).setCalcDisplay("2")

        presenter.handleDigit(3)
        verify(view).setCalcDisplay("23")
    }

    @Test
    fun `handling dot 0 5 + 1 =`() {
        val view = mock<CalcView>()
        val model = mock<CalcModel>()
        val presenter = CalcPresenterImpl(view, model)

        presenter.handleDot()
        verify(view).setCalcDisplay("0.")

        presenter.handleDigit(0)
        verify(view).setCalcDisplay("0.0")

        presenter.handleDigit(5)
        verify(view).setCalcDisplay("0.05")

        presenter.handleOp(CalcModel.Op.ADD)
        verify(view, times(2)).setCalcDisplay("0.05")

        presenter.handleDigit(4)
        verify(view).setCalcDisplay("4")

        whenever(model.binaryOp(any(), any(), any())).thenReturn(4.05)
        presenter.handleEquals()
        verify(view).setCalcDisplay("4.05")
    }

    @Test
    fun `handling 5 C`() {
        val view = mock<CalcView>()
        val model = mock<CalcModel>()
        val presenter = CalcPresenterImpl(view, model)

        presenter.handleDigit(5)
        verify(view).setCalcDisplay("5")

        presenter.handleClear()
        verify(view).setCalcDisplay("0")
    }

    @Test
    fun `handling + =`() {
        val view = mock<CalcView>()
        val model = mock<CalcModel>()
        val presenter = CalcPresenterImpl(view, model)

        presenter.handleDigit(2)
        presenter.handleOp(CalcModel.Op.ADD)
        presenter.handleEquals()
        verify(view, times(3)).setCalcDisplay("2")
    }

    @Test
    fun `save and load`() {
        val view = mock<CalcView>()
        val model = mock<CalcModel>()
        val presenter = CalcPresenterImpl(view, model)
        val bundle = mock<Bundle>()

        presenter.save(bundle)
        verify(bundle).putParcelable(eq("CalcPresenterImpl"), any())

        whenever(bundle.getParcelable<PresenterState>("CalcPresenterImpl"))
            .thenReturn(PresenterState())
        presenter.load(bundle)
        verify(bundle).getParcelable<PresenterState>("CalcPresenterImpl")
    }
}