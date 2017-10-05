package io.zgeeks.algorithm.hackerrank.queenattack

import java.util

import io.zgeeks.algorithm.hackerrank.queenattack.QueenAttack.{Active, Direction, Square}
import org.scalatest.{FlatSpec, Matchers}

class LinksBuilderTest extends FlatSpec with Matchers {

  "A square of chess " should "spread in creation" in {
    val s1_1 = new Active(1, 1)
    val s1_2 = new Active(1, 2)
    val s1_3 = new Active(1, 3)
    val graph = new util.HashSet[Square]
    graph.add(s1_1)
    graph.add(s1_2)
    graph.add(s1_3)
    val squareBuilder = LinksBuilder.builder()
    squareBuilder.source(s1_1).limit(4)
    val map : util.Map[Direction, Square] = squareBuilder.build(graph)
    map.toString
    s1_1.merge(map)
    s1_1.toString
  }
}
