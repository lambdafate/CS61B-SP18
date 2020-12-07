public class ArrayDeque<T> implements Deque<T> {
    private static final int INIT_SIZE = 8;
    private static final int INCREASE = 2;
    private static final double RATE = 0.25;

    private Object[] items;
    private int first;
    private int last;
    private int size;

    public ArrayDeque() {
        items = new Object[INIT_SIZE];
        first = 0;
        last = 1;
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        items = new Object[other.items.length];
        for (int i = 0; i < other.size(); i++) {
            items[i] = other.get(i);
        }
        size = other.size();
        first = items.length - 1;
        last = size;
    }

    /* Adds an item of type T to the front of the deque. */
    @Override
    public void addFirst(T item) {
        checkWhenAdd();
        items[first] = item;
        first = forwardIndex(first);
        size += 1;
    }

    /* Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T item) {
        checkWhenAdd();
        items[last] = item;
        last = backIndex(last);
        size += 1;
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
        if (isEmpty()) {
            return null;
        }
        first = backIndex(first);
        T ret = (T) items[first];
        items[first] = null;
        size -= 1;

        checkWhenRemove();
        return ret;
    }

    /* Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        last = forwardIndex(last);
        T ret = (T) items[last];
        items[last] = null;
        size -= 1;

        checkWhenRemove();
        return ret;
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque! */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return (T) items[(first + 1 + index) % items.length];
    }


    private void resize(int newSize) {
        Object[] tmp = new Object[newSize];
        for (int i = 0; i < size; i++) {
            tmp[i] = this.get(i);
        }

        // update first and last
        items = tmp;
        first = items.length - 1;
        last = size;
    }


    private int forwardIndex(int index) {
        index -= 1;
        if (index < 0) {
            return items.length - 1;
        }
        return index;
    }

    private int backIndex(int index) {
        return (index + 1) % items.length;
    }

    private double getRate() {
        return 1.0 * size / items.length;
    }

    private void checkWhenAdd() {
        if (size == items.length) {
            resize(size * INCREASE);
        }
    }

    private void checkWhenRemove() {
        if (items.length > 16 && getRate() < RATE) {
            resize(size * 2);
        }
    }
}
