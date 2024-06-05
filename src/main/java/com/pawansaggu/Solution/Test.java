package Solution;

import java.util.*;

class Node {
    Node next;
    int value;
    int key;
    
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

class LinkedList {
    Node root;
    Node tail;
    
    public LinkedList() {
        this.root = null;
    }
    
    public void addValue(int key, int val) {
        if (this.root == null) {
            this.root = new Node(key, val);
            this.tail = this.root;
        } else {
            Node ptr = this.root;

            while (ptr != null) {
                if (ptr.key == key) {
                    ptr.value = val;
                    
                    return;
                }

                ptr = ptr.next;
            }

            this.tail.next = new Node(key, val);
            this.tail = this.tail.next;
        }
    }
    
    public int getValue(int key) {
        Node ptr = this.root;
        
        while (ptr != null) {
            if (ptr.key == key) {
                return ptr.value;
            }
            
            ptr = ptr.next;
        }
        
        return Integer.MIN_VALUE;
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
    
    public void addKey(int key, int value) {
        int index = this.getHash(key);
        
        if (this.list.get(index) == null) {
            this.list.set(index, new LinkedList());
        }
        
        LinkedList ptr = this.list.get(index);

        ptr.addValue(key, value);
    }
    
    public int getValue(int key) {
        int index = this.getHash(key);
        
        if (this.list.get(index) == null) {
            return Integer.MIN_VALUE;
        }
        
        LinkedList ptr = this.list.get(index);
        
        return ptr.getValue(key);
    }
    
    private int getHash(int key) {
        return key % this.list.size();
    }
}

class Test {
    public static void main(String[] args) {
      CustomMap map = new CustomMap(100);
      map.addKey(1, 2);
      map.addKey(1, 5);

    }
}