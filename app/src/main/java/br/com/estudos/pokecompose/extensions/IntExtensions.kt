package br.com.estudos.pokecompose.extensions

fun Int.toDoubleFormat(decimals: Int):String {
    return (this.toDouble()/10).toString().format("%.${decimals}f", this)
}