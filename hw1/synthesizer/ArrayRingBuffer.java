package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        //   Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.

        //T[] temp_arr = new T[this.capacity];
        this.rb = (T []) new Object[capacity];
        //rb = new BoundedQueue<T>();
        this.capacity = capacity;
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {

        if (this.isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        this.fillCount += 1;
        last += 1;
        if (last == capacity) {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {

        if (this.isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T res = rb[first];
        //rb[first] = null;
        this.fillCount -= 1;
        first += 1;
        if (first == capacity) {
            first = 0;
        }
        return res;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {

        if (this.isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return rb[first];
    }


    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<T> {
        private int ptr;
        private BufferIterator() {
            ptr = 0;
        }
        @Override
        public boolean hasNext() {
            return (ptr != capacity - 1);
        }
        @Override
        public T next() {
            T returnItem = rb[ptr];
            ptr += 1;
            return returnItem;
        }

    }
}
