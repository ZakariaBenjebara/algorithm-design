package io.zgeeks.algorithm.hackerrank

import java.lang.Math._

import scala.collection.mutable

class DiagonalDiff2(val matrix: Matrix) {
    def calculate() = matrix.diagonalDiff
}

private[hackerrank] class Matrix(val size: Int, val values: Map[Int, mutable.MutableList[Int]]) {

  def this(size: Int) {
      this(size, Range.apply(0, size) map (index => index -> new mutable.MutableList[Int]) toMap)
  }

  private[hackerrank] def diagonalDiff(lowerLimit: Int, upperLimit: Int) : (Int, Int) = {
    values.map(key => (key._2.get(key._1 - lowerLimit).get, key._2.get(abs(key._1 - upperLimit)).get))
      .foldLeft[(Int, Int)]((0, 0))((a, b) => (a._1 + b._1 , a._2 + b._2))
  }

  private[hackerrank] def diagonalDiff : Int = diagonalDiff(0, size - 1) match {
    case (t1, t2) => abs(t1 - t2)
    case _ => throw new IllegalStateException("Calculation error")
  }

  private[hackerrank] def put(dim: Int, elements: Int*) {
    if (dim < 0 || dim >= size) throw new IllegalStateException("incorrect dimension")
    elements.foreach(e => values.get(dim).get += e)
  }
}

object DiagonalDiff2 {
  def calculate(matrix: Matrix) = new DiagonalDiff2(matrix).calculate()
}