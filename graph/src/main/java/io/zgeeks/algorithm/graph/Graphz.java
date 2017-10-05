package io.zgeeks.algorithm.graph;

import java.util.Set;

public interface Graphz<N> {

    Set<N> nodes();

    Set<DirectedLink<N>> edges();
}
