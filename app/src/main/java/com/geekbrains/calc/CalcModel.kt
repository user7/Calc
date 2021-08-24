package com.geekbrains.calc

interface CalcModel {
    enum class Op {
        ADD,
        SUB,
        MUL,
        DIV
    }

    fun binaryOp(a: Double, b: Double, op: Op): Double
}