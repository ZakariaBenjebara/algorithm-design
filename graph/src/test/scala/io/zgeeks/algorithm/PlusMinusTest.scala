package io.zgeeks.algorithm

import org.scalatest.{FlatSpec, Matchers}

class PlusMinusTest extends FlatSpec with Matchers {

  "Given an array of integers which fraction of positive, negative and zeroes" should " be calculating" in {
    val plusMinus = new PlusMinus(Array(-4, 3, -9, 0, 4, 1))
    plusMinus.compute()

    "%.3f".format(plusMinus.positiveRate) should equal("0,500")
    "%.3f".format(plusMinus.negativeRate) should equal("0,333")
    "%.3f".format(plusMinus.zeroesRate) should equal("0,167")
  }

}
