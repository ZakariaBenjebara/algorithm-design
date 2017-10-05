//package io.zgeeks.util;
//
//import java.util.List;
//import java.util.function.Function;
//
//@Curring
//public interface Convertes<R> {
//
//
//    static Function<String, Function<Integer, Function<List<String>, List<R>>>> toCure() {
//        return s -> i -> list -> list.stream().filter(str -> str.equals(s)).mapToInt(str -> str.hashCode() + i).toArray();
//    }
//
//
//    static Convertes_Curring curring() {
//        return new Convertes_Curring();
//    }
//}
