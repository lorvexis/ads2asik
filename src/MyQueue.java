public class MyQueue<T> {
    private MyNode<T> front;
    private MyNode<T> rear;

    public void enqueue(T data) {
        MyNode<T> newNode = new MyNode<>(data);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public T dequeue() {
        if (isEmpty()) return null;
        T data = front.data;
        front = front.next;
        if (front == null) rear = null;
        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public MyNode<T> getFront() {
        return front;
    }
}