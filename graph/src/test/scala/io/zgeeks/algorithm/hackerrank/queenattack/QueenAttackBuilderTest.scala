package io.zgeeks.algorithm.hackerrank.queenattack

import io.zgeeks.algorithm.hackerrank.queenattack.QueenAttack.Active
import org.scalatest.{FlatSpec, Matchers}

class QueenAttackBuilderTest extends FlatSpec with Matchers {

  import scala.collection.JavaConverters._

  "A square of chess " should "spread in creation" in {
    val board =  QueenAttackBuilder.createBoard(2)
    board.size should equal(16)
    val onlyActive = board.asScala.filter(_.isInstanceOf[Active])
    onlyActive.size should equal(4)
  }
}
