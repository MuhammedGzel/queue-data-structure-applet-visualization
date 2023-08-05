package queueapplet;

class Node {

    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        next = null;
    }
}

public class QueueClass {

    Node front;
    Node rear;
    int elementCount;

    public QueueClass() {
        front = null;
        rear = null;
        elementCount = 0;
    }

    boolean isEmpty() {
        return elementCount == 0;
    }

    void enQueue(int newData) {
        Node newNode = new Node(newData);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        elementCount++;
    }

    Node deQueue() {
        if (isEmpty()) {
            return null;
        }

        Node removed = front;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        elementCount--;
        return removed;
    }

    Node returnFront() {
        return front;
    }

    void printQueue() {
        if (!isEmpty()) {
            Node tmp = front;
            while (tmp != null) {
                System.out.print(tmp.data);
                tmp = tmp.next;
                if (tmp != null) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
