package Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Leetcode {
    public static void main(String args[]) {
        Solution s = new Solution();
        int ranges[] = {0,5,0,3,3,3,1,4,0,4};
        int output = s.minTaps(9, ranges);
        System.out.println(output);
    }
}
class Range {
    int left;
    int right;

    Range(int left, int right) {
        this.left = left;
        this.right = right;
    }
}

class Solution {
    private boolean haveReachedEnd(Range currentRange, int n) {
        if(currentRange.right == n) {
            return true;
        }
        return false;
    }

    public int minTaps(int n, int[] ranges) {
        List<Range> rangePairs = new ArrayList<>();
        Range prev = null;
        int taps = 0;
        PriorityQueue<Range> pq = new PriorityQueue<>(new Comparator<Range>() {
            public int compare(Range a, Range b) {
                return b.right - a.right;
            }
        });

        for(int i=0; i<ranges.length; i++) {
            rangePairs.add(new Range(Math.max(0, i-ranges[i]), Math.min(i+ranges[i], n)));
        }

        Collections.sort(rangePairs, new Comparator<Range>() {
            public int compare(Range a, Range b) {
                if(a.left == b.left) {
                    return b.right - a.right;
                } 
                return a.left - b.left;
            }
        });

        for(int i=0; i<rangePairs.size(); i++) {
            if(prev!=null && haveReachedEnd(prev, n)) {
                return taps;
            }

            if(prev == null) {
                prev = rangePairs.get(i);
                taps++;
            } else if (rangePairs.get(i).left <= prev.right) {
                pq.add(rangePairs.get(i));
            } else if (rangePairs.get(i).left > prev.right) {
                if(pq.size()==0 || pq.peek().right < rangePairs.get(i).left) {
                    return -1;
                }
                taps++;
                prev = pq.poll();
                pq.add(rangePairs.get(i));
            }
        }

        return -1;
    }
}
