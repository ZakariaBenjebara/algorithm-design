package io.zgeeks.algorithm.tree;

import org.immutables.value.Value;

@Value.Immutable
interface BinaryTree<T> {
    T key();
//    BinaryTree<T> parent();
    BinaryTree<T> left();
    BinaryTree<T> right();
}
