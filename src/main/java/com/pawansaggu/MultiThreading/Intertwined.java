package MultiThreading;

import java.util.concurrent.locks.ReentrantLock;

// class Printer extends Thread {
//     private int count;
//     private ReentrantLock lockObject;

//     Printer(int count, ReentrantLock lockObject) {
//         this.count = count;
//         this.lockObject = lockObject;
//     }

//     public void run() {
//         int start = 0;

//         while(start < this.count) {
//             synchronized (this.lockObject) {
//                 System.out.println(Thread.currentThread().getName() + " " + start);
//                 start++;
//                 try {
//                     lockObject.notify();
//                     lockObject.wait();
//                 } catch (Exception e) {

//                 }
//             }
//         }
//     }
// }

class Printer extends Thread {
    private int count;
    private ReentrantLock lockObject;

    Printer(int count, ReentrantLock lockObject) {
        this.count = count;
        this.lockObject = lockObject;
    }

    public void run() {
        int start = 0;

        while(start < this.count) {
            boolean canLock = lockObject.tryLock();
            if (canLock) {
                System.out.println(Thread.currentThread().getName() + " " + start);
                start++;
                lockObject.unlock();
            }
        }
    }
}

class Intertwined {
    public static void main(String args[]) {
        ReentrantLock rlock = new ReentrantLock();
        Thread PrinterT1 = new Printer(10, rlock);
        Thread printerT2 = new Printer(10, rlock);
        PrinterT1.start();
        printerT2.start();
        try {
            PrinterT1.join();
            printerT2.join();
        } catch (Exception e) {

        }
    }
}