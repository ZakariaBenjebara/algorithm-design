package io.zgeeks.algorithm.graph

trait Graph[V, N] {
  def vertices(): List[_ <: V];
  def nodes(): List[_ <: N];
}
