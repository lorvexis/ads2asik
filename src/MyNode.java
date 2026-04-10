public class MyNode<T> {
    public T data;
    public MyNode<T> next;

    public MyNode(T data) {
        this.data = data;
        this.next = null;
    }
}