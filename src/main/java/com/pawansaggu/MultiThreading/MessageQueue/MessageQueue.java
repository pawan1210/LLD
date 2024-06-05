package MultiThreading.MessageQueue;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private Queue<String> queue;
    private int LIMIT;

    public MessageQueue(int LIMIT) {
        this.LIMIT = LIMIT;
        this.queue = new LinkedList<>();
    }

    public synchronized void produce(String message) throws InterruptedException{
        while (this.queue.size() == this.LIMIT) {
            wait();
        }

        this.queue.add(message);
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }

        System.out.println("message received: "+ this.queue.poll());

        notifyAll();
    }
}


class Main {
    public static class Producer implements Runnable {
        private MessageQueue queue;

        public Producer(MessageQueue queue) {
            this.queue = queue;
        }

        public void run() {
            try {
                this.queue.produce("abc");
            } catch (Exception e) {

            }
        }
    }

    public static class Consumer implements Runnable {
        private MessageQueue queue;

        public Consumer(MessageQueue queue) {
            this.queue = queue;
        }

        public void run() {
            try {
                this.queue.consume();
            } catch (Exception e) {

            }
        }
    }

    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);

        thread1.start();
        thread2.start();

        // try {
        //     thread1.join();
        //     thread2.join();
        // } catch (Exception e) {

        // }
    }
}

