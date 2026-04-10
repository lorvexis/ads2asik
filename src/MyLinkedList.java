public class MyLinkedList<T> {
    private MyNode<T> head;
    private int size = 0;

    public void add(T data) {
        MyNode<T> newNode = new MyNode<>(data);
        if (head == null) {
            head = newNode;
        } else {
            MyNode<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public MyNode<T> getHead() {
        return head;
    }
}