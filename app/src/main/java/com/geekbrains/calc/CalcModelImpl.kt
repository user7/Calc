package com.geekbrains.calc

class CalcModelImpl : CalcModel {
    override fun binaryOp(a: Double, b: Double, op: CalcModel.Op): Double {
        return when (op) {
            CalcModel.Op.ADD -> a + b
            CalcModel.Op.SUB -> a - b
            CalcModel.Op.MUL -> a * b
            CalcModel.Op.DIV -> a / b
        }
    }
}