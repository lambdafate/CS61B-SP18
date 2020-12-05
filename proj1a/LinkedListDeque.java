public class LinkedListDeque<T> {
    private static class Node<T> {
        public T value;
        public Node<T> prev;
        public Node<T> next;

        public Node(T value, Node<T> prev, Node<T> next) {
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

    public LinkedListDeque(LinkedListDeque<T> other) {
        this();
        for (int i = 0; i < other.size(); i++) {
            this.addLast(other.get(i));
        }
    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        size += 1;

        Node<T> node = new Node<>(item, null, null);
        addAfter(sentinel, node);
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        size += 1;

        Node<T> node = new Node<>(item, null, null);
        addAfter(sentinel.next, node);
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space.
    Once all the items have been printed, print out a new line. */
    public void printDeque() {
        Node<T> work = sentinel.next;
        while (work != sentinel) {
            if (work != sentinel.next) {
                System.out.print(" ");
            }
            System.out.print(work.value);
            work = work.next;
        }
        System.out.print("\n");
    }

    /* Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (!isEmpty()) {
            size -= 1;
            T ret = sentinel.next.value;
            removeAfter(sentinel);
            return ret;
        }
        return null;
    }

    /* Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (!isEmpty()) {
            size -= 1;
            T ret = sentinel.prev.value;
            removeAfter(sentinel.prev.prev);
            return ret;
        }
        return null;
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque! */
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
    }

    private void removeAfter(Node<T> node1) {
        Node<T> node2 = node1.next.next;
        node1.next = node2;
        node2.prev = node1;
    }

}
