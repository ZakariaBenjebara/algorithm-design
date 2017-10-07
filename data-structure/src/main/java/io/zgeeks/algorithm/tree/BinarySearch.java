package io.zgeeks.algorithm.tree;


public interface BinarySearch {

    default <T extends Comparable<T>> BinaryTree<T> query(BinaryTree<T> source, T key) {
        if (source == null || source.key().equals(key)) {
            return source;
        }
        if (key.compareTo(source.key()) > 0) {
            return query(source.right(), key);
        }
        return query(source.left(), key);
    }
}
