public class ArrayDeque<T> {
    private static final int INIT_SIZE = 8;
    private static final int INCREASE  = 2;

    private Object[] items;
    private int first;
    private int last;
    private int size;

    public ArrayDeque(){
        items = new Object[INIT_SIZE];
        first = 0;
        last  = 1;
        size  = 0;
    }

    public ArrayDeque(ArrayDeque other){
        this();
    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if(size == items.length){
            resize(size * INCREASE);
        }
        items[first] = item;
        forwardFirst();
        size += 1;
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if(size == items.length){
            resize(size * INCREASE);
        }
        items[last] = item;
        backLast();
        size += 1;
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
        int index = (first + 1) % items.length;
        int tmpIndex = index;
        for (int i = 0; i < size; i++) {
            if(index != tmpIndex){
                System.out.print(" ");
            }
            System.out.print(items[index]);
            index = (index + 1) % items.length;
        }
        System.out.print("\n");
    }

    /* Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if(isEmpty()){ return null; }
        backFirst();
        T ret = (T)items[first];
        items[first] = null;
        size -= 1;

        if(items.length > 16 && getRate() < 0.25){
            resize(size * 2);
        }
        return ret;
    }

    /* Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if(isEmpty()){ return null; }
        forwardLast();
        T ret = (T)items[last];
        items[last] = null;
        size -= 1;

        if(items.length > 16 && getRate() < 0.25){
            resize(size * 2);
        }
        return ret;
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if(index < 0 || index >= size) {
            return null;
        }
        return (T)items[(first + 1 + index) % items.length];
    }


    private void resize(int newSize){
        Object[] tmp = new Object[newSize];
        int index = (first + 1) % items.length;
        for (int i = 0; i < size; i++) {
            tmp[i] = items[index];
            index = (index + 1) % items.length;
        }

        // update first and last
        items = tmp;
        first = items.length - 1;
        last  = size;
    }
    
    private void backFirst(){
        first = (first + 1) % items.length;
    }
    
    private void forwardFirst(){
        first -= 1;
        if(first < 0){
            first = items.length - 1;
        }
    }
    
    private void backLast(){
        last = (last + 1) % items.length;
    }
    
    private void forwardLast(){
        last -= 1;
        if(last < 0){
            last = items.length - 1;
        }
    }
    private double getRate(){
        return 1.0 * size / items.length;
    }

}
