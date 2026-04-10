public class MyStack<T> {
    private MyNode<T> top;

    public void push(T data) {
        MyNode<T> newNode = new MyNode<>(data);
        newNode.next = top;
        top = newNode;
    }

    public T pop() {
        if (isEmpty()) return null;
        T data = top.data;
        top = top.next;
        return data;
    }

    public T peek() {
        return (isEmpty()) ? null : top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public MyNode<T> getTop() {
        return top;
    }
}