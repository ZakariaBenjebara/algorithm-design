package io.zgeeks.algorithm.hackerrank

import org.scalatest.{FlatSpec, Matchers}

class DiagonalDiff2Test extends FlatSpec with Matchers {

  "A matrix diagonal " should "calculating " in {
    val matrix = new Matrix(3)
    matrix.put(0,   11,   2,    4)
    matrix.put(1,    4,   5,    6)
    matrix.put(2,   10,   8,  -12)

    DiagonalDiff2.calculate(matrix) should equal(15)
  }

  "A matrix diagonal " should "equals to one " in {
    val matrix = new Matrix(3)
    matrix.put(0, 1, 1, 1)
    matrix.put(1, 1, 1, 1)
    matrix.put(2, 1, 1, 1)

    DiagonalDiff2.calculate(matrix) should equal(0)
  }

  "A diagonal diff of matrix with dim = 9 " should "calculating " in {
  /*
    6 6 7 -10 9 -3 8 9 -1
    9 7 -10 6 4 1 6 1 1
    -1 -2 4 -6 1 -4 -6 3 9
    -8 7 6 -1 -6 -6 6 -7 2
    -10 -4 9 1 -7 8 -5 3 -5
    -8 -3 -4 2 -3 7 -5 1 -5
    -2 -7 -4 8 3 -1 8 2 3
    -3 4 6 -7 -7 -8 -3 9 -6
    -2 0 5 4 4 4 -3 3 0
  */
    val matrix = new Matrix(9)
    matrix.put(0,    6,    6,    7,  -10,    9,   -3,    8,    9,   -1)
    matrix.put(1,    9,    7,  -10,    6,    4,    1,    6,    1,    1)
    matrix.put(2,   -1,   -2,    4,   -6,    1,   -4,   -6,    3,    9)
    matrix.put(3,   -8,    7,    6,   -1,   -6,   -6,    6,   -7,    2)
    matrix.put(4,  -10,   -4,    9,    1,   -7,    8,   -5,    3,   -5)
    matrix.put(5,   -8,   -3,   -4,    2,   -3,    7,   -5,    1,   -5)
    matrix.put(6,   -2,   -7,   -4,    8,    3,   -1,    8,    2,    3)
    matrix.put(7,   -3,    4,    6,   -7,   -7,   -8,   -3,    9,   -6)
    matrix.put(8,   -2,    0,    5,    4,    4,    4,   -3,    3,    0)

    DiagonalDiff2.calculate(matrix) should equal(52)
  }
}
