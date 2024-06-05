package SolutionWithTimeStamp;

import java.util.*;

class Pair {
    int value;
    int timestamp;

    Pair (int value, int timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }
}

class Node {
    Node next;
    List<Pair> values;
    int key;
    
    public Node(int key) {
        this.key = key;
        this.values = new ArrayList<>();
        this.next = null;
    }

    public void addTimeStamp(int val, int timestamp) {
        this.values.add(new Pair(val, timestamp));
    }

    public int getValue(int timestamp) {
        for (int i=0; i<this.values.size(); i++) {
            if (this.values.get(i).timestamp == timestamp) {
                return this.values.get(i).value;
            }
        }

        return values.get(values.size() - 1).value;
    }
}

class LinkedList {
    Node root;
    Node tail;
    
    public LinkedList() {
        this.root = null;
    }
    
    public void addValue(int key, int val, int timestamp) {
        if (this.root == null) {
            this.root = new Node(key);
            this.root.addTimeStamp(val, timestamp);
            this.tail = this.root;
        } else {
            Node ptr = this.root;

            while (ptr != null) {
                if (ptr.key == key) {
                    ptr.addTimeStamp(val, timestamp);
                
                    return;
                }

                ptr = ptr.next;
            }

            this.tail.next = new Node(key);
            this.tail.next.addTimeStamp(val, timestamp);
            this.tail = this.tail.next;
        }
    }
    
    public int getValue(int key, int timestamp) {
        Node ptr = this.root;
        
        while (ptr != null) {
            if (ptr.key == key) {
                return ptr.getValue(timestamp);
            }
            
            ptr = ptr.next;
        }
        
        return Integer.MIN_VALUE;
    }    
}

class ArrayList {
    int arr[];

    ArrayList(int size) {
        this.arr = new int[size];
    }
}

class CustomMap {
    private List<LinkedList> list;
    
    public CustomMap(int size) {
        this.list = new ArrayList<>();
        
        for (int i=0; i<size; i++) {
            this.list.add(null);
        }
    }
    
    public void addKey(int key, int value, int timestamp) {
        int index = this.getHash(key);
        
        if (this.list.get(index) == null) {
            this.list.set(index, new LinkedList());
        }
        
        LinkedList ptr = this.list.get(index);

        ptr.addValue(key, value, timestamp);
    }
    
    public int getValue(int key, int timestamp) {
        int index = this.getHash(key);
        
        if (this.list.get(index) == null) {
            return Integer.MIN_VALUE;
        }
        
        LinkedList ptr = this.list.get(index);
        
        return ptr.getValue(key, timestamp);
    }
    
    private int getHash(int key) {
        return key % this.list.size();
    }
}

class Solution {
    public static void main(String[] args) {
      CustomMap map = new CustomMap(100);

      map.addKey(1, 2, 1);
      map.addKey(1, 2, 2);

      System.out.println(map.getValue(1, 2));
      System.out.println(map.getValue(1, 3));
    }
}