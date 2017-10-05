package io.zgeeks.algorithm.graph

class UndirectedGraph[V, N](val listOfVertex: List[_ <: V], val listOfNode: List[_ <: N]) extends Graph[V, N] {

  override def vertices(): List[_ <: V] = {
    listOfVertex
  }

  override def nodes(): List[_ <: N] = {
    listOfNode
  }
}
