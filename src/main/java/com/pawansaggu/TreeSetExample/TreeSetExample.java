package TreeSetExample;

import java.util.*;

class Pair {
    int value;
    int index;

    Pair(int index, int value) {
        this.index = index;
        this.value = value;
    }
}

public class TreeSetExample {
    public static void printSet(TreeSet<Pair> set) {
        for (Pair p: set) {
            System.out.print(p.value+" ");
        }

        System.out.println();
    }

    public static void main(String args[]) {
        int arr[] = {12, 5, 13, 1, -5, 20, 6, 7};
        TreeSet <Pair> set = new TreeSet<>(new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                if (a.value != b.value) {
                    return b.value - a.value;
                } else {
                    return a.index - b.index;
                }
            }
        });

        for (int i=0; i<arr.length; i++) {
            printSet(set);
            Pair largerPair = set.ceiling(new Pair(0 , arr[i]));

            if (largerPair != null) {
                int largerElements = i - largerPair.index;
                System.out.println(arr[i]+" "+"larger elements are " + largerElements);
            }

            set.add(new Pair(i, arr[i]));
        }
    }
}