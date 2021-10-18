package cat.copernic.jmendezv

import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt


/**
 * Optimització de codi amb JUnit
 */

data class Punto(val x: Double, val y: Double)

/*
*
* Verificació de codi
*
* Índice de masa corporal
*
* imc = weight / height^2
* */
fun IMC(weight: Double, height: Double): Double = weight / height.pow(2)

/*
* Verificació de codi
*
* https://en.wikipedia.org/wiki/Quadratic_equation
*
* (-b ± sqrt(b^2 - 4ac)) / 2a
* */
fun equSegundoGrado(a: Double, b: Double, c: Double): Pair<Double, Double> =
    Pair((-b + sqrt((b.pow(2) - (4 * a * c)))) / (2 * a), (-b - sqrt((b.pow(2) - (4 * a * c)))) / (2 * a))

/*
* Verificació de codi
*
* Cálculo de la distancia entre dos puntos
*
* distance = √[(x2 – x1)^2 + (y2 – y1)^2]
* */
fun distanciaEntre2Puntos(p1: Punto, p2: Punto): Double =
    sqrt((p1.x - p2.x).pow(2) + (p1.y - p2.y).pow(2))

/*
* Verificació de codi
*
* Cáculo de la pendiente de una recta
*
* slope = (y2 – y1) / (x2 – x1)
* */
fun Pendiente(p1: Punto, p2: Punto): Double = (p2.y - p1.y) / (p2.x - p1.x)

/*
* Verificació de codi
*
* Cálculo del punto medio de una recta
*
* midpoint = ((x1+x2)/2, (y1+y2)/2)
* */
fun puntoMedio(p1: Punto, p2: Punto): Punto =
    Punto((p1.x + p2.x) / 2, (p1.y + p2.y) / 2)

/*
* Verificació de codi
*
* */
fun calificacion(score: Double): String {
    val roundedScore = floor(score * 100) / 100
    return when (roundedScore) {
        in 0.0..4.99 -> "No supera"
        in 5.0..5.99 -> "Supera"
        in 6.0..6.99 -> "Bien"
        in 7.0..8.99 -> "Notable"
        in 9.0..10.0 -> "Excel·lent"
        else -> "Nota invalida"
    }
}

/*
* Verificació de codi
*
* Encuentra el menor y mayor
*
* [2,3,1,4,7,6,5] = (1,7)
* [] = IllegalArgumentException
* */
fun maxMin(list: List<Int>): Pair<Int, Int> {
    if (list.isEmpty()) throw IllegalArgumentException("Empty list")
    val listSorted = list.sorted()
    return Pair(listSorted.first(), listSorted.last())
}

/*
* Verificació de codi
*
* Cálculo del punto más cercano a point. points es una lista de tipo Point
*
* */
fun masCercano(point: Punto, vararg points: Punto): Punto {
    var p: Punto = points.first()
    var distance = distanciaEntre2Puntos(point, p)

    for (currentPoint in points) {
        val currentDistance = distanciaEntre2Puntos(point, currentPoint)
        if (currentDistance < distance) {
            distance = currentDistance
        }
    }

    return p
}
