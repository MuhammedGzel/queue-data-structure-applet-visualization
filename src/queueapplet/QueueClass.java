
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

    void enQueue(Node newNode) {
        if (front == null) {
            rear = newNode;
            front = newNode;
            elementCount++;
        } else {
            rear.next = newNode;
            rear = newNode;
            elementCount++;
        }
    }

    Node deQueue() {
        Node removed = null;
        if (front != null) {
            if (front.next != null) {
                removed = front;
                front = front.next;
                elementCount--;
            } else {
                removed = front;
                front = null;
                rear = null;
                elementCount--;
            }
        }
        return removed;
    }

    Node returnFront() {
        return front;
    }

    void printQueue() {
        if (!isEmpty()) {
            Node tmp = front;
            for (int i = 0; i < elementCount; i++) {
                System.out.print(tmp.data + " ");
                tmp = tmp.next;
            }
        }

    }

}
