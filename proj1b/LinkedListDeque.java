public class LinkedListDeque<T> implements Deque<T> {
    private static class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;

        private Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node<T> sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node<>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        this();
        for (int i = 0; i < other.size(); i++) {
            this.addLast((T) other.get(i));
        }
    }

    /* Adds an item of type T to the front of the deque. */
    @Override
    public void addFirst(T item) {
        Node<T> node = new Node<>(item, null, null);
        addAfter(sentinel, node);
    }

    /* Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T item) {
        Node<T> node = new Node<>(item, null, null);
        addAfter(sentinel.prev, node);
    }

    /* Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /* Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space.
    Once all the items have been printed, print out a new line. */
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                System.out.print(" ");
            }
            System.out.print(this.get(i));
        }
        System.out.print("\n");
    }

    /* Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    @Override
    public T removeFirst() {
        if (!isEmpty()) {
            T ret = sentinel.next.value;
            removeAfter(sentinel);
            return ret;
        }
        return null;
    }

    /* Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    @Override
    public T removeLast() {
        if (!isEmpty()) {
            T ret = sentinel.prev.value;
            removeAfter(sentinel.prev.prev);
            return ret;
        }
        return null;
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque! */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> work = sentinel.next;
        int i = 0;
        while (i++ < index) {
            work = work.next;
        }
        return work.value;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return sentinel.next.value;
        }
        Node<T> first = sentinel.next;
        removeAfter(sentinel);
        T ret = getRecursive(index - 1);
        addAfter(sentinel, first);
        return ret;
    }

    private void addAfter(Node<T> node1, Node<T> node) {
        Node<T> node2 = node1.next;
        node.next = node2;
        node2.prev = node;
        node.prev = node1;
        node1.next = node;
        size += 1;
    }

    private void removeAfter(Node<T> node1) {
        Node<T> node2 = node1.next.next;
        node1.next = node2;
        node2.prev = node1;
        size -= 1;
    }
}
