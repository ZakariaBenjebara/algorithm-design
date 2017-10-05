package io.zgeeks.algorithm.hackerrank.queenattack

import org.scalatest.{FlatSpec, Matchers}

class QueenAttackTest extends FlatSpec with Matchers {

  "A square of chess " should "spread" in {
    val board =  QueenAttackBuilder.createBoard(4)
    val queenAttack = new QueenAttack(board)

    queenAttack.numberOfAttacks(4, 4) should equal(9)
  }

  "A square of chess " should "spread equals to ten" in {
    val board =  QueenAttackBuilder.createBoard(5)
    QueenAttackBuilder.markAsObstacle(5, 5, board)
    QueenAttackBuilder.markAsObstacle(4, 2, board)
    QueenAttackBuilder.markAsObstacle(2, 3, board)
    val queenAttack = new QueenAttack(board)

    queenAttack.numberOfAttacks(4, 3) should equal(10)
  }
}
