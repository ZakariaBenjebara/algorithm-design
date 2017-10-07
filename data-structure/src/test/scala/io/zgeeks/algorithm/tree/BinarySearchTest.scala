package io.zgeeks.algorithm.graph

import org.scalatest.{FlatSpec, Matchers}

class GraphTest extends FlatSpec with Matchers {

  "A Graphz " should "have nodes and vertices" in {
    val graph = new DirectedGraph[String, String](List("v1", "v2"), List("n1", "n2"))
    graph.nodes should have length 2
    graph.vertices should have length 2
  }
}
