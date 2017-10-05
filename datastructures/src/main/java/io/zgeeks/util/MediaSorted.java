package io.zgeeks.util;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by benjebarazakaria on 21/08/2017.
 */
public final class MediaSorted {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sn = new Scanner(System.in);
        int size = sn.nextInt();
        int[] a = new int[size];
        int itr = 0;
        do {
            a[itr] = sn.nextInt();
            itr++;
        } while (itr != size);

        int[] reverse = reverse(a);
        for (int i = 0; i < size; i++) {
            System.out.print(reverse[i]);
        }
    }

    private static int[] reverse(int[] a) {
        int length = a.length;
        int[] r = new int[length];
        for (int i = 0; i < length; i++) {
            r[i] = a[length - i - 1];
        }
        return r;
    }
}
