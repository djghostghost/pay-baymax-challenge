package immutable;

import java.util.NoSuchElementException;

public final class ImmutableQueue<T> {

    /**
     * Pointer to first node.
     */
    private Node<T> first;
    /**
     * Pointer to last node.
     */
    private Node<T> last;

    public ImmutableQueue() {
    }

    /**
     * @param t Immutable Queue Element
     */
    public ImmutableQueue(T t) {
        first = new Node<>(t, null, null);
        last = first;
    }

    /**
     * Generate new Immutable Queue based original Immutable iq
     *
     * @param iq original immutable queue
     */
    public ImmutableQueue(ImmutableQueue<T> iq) {
        for (Node<T> head = iq.first; head != null; head = head.next) {
            if (this.isEmpty()) {
                first = new Node<>(head.ele, null, null);
                last = first;
            } else {
                Node<T> n = new Node<>(head.ele, last, null);
                last.next = n;
                last = n;
            }
        }
    }

    /**
     * return the queue that add object to this queue tail without modifying this queue
     *
     * @param t element
     * @return immutable.ImmutableQueue
     * @throws IllegalArgumentException
     */
    public ImmutableQueue<T> enQueue(T t) {

        if (t == null) {
            throw new IllegalArgumentException();
        }

        ImmutableQueue<T> immutableQueue;
        if (this.isEmpty()) {
            // Queue is Empty. enqueue data and set first point to t
            immutableQueue = new ImmutableQueue<>(t);
        } else {
            immutableQueue = new ImmutableQueue<>(this);
            Node<T> n = new Node<>(t, immutableQueue.last, null);
            immutableQueue.last.next = n;
            immutableQueue.last = n;
        }
        return immutableQueue;
    }

    /**
     * return the queue that removed first object without modifying this queue
     *
     * @return immutable.ImmutableQueue
     * @throws NoSuchElementException
     */
    public ImmutableQueue<T> deQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        ImmutableQueue<T> q = new ImmutableQueue<>(this);
        if (q.first.next != null) {

            q.first.next.prev = null;
            Node<T> temp = q.first;
            q.first = q.first.next;
            temp.next = null;
        } else {
            q.first = null;
            q.last = null;
        }
        return q;
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this queue.
     *
     * @return T
     */
    public T head() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return first.ele;
    }


    public T tail() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return last.ele;
    }


    /**
     * Returns {@code true} if this queue contains no elements.
     *
     * @return {@code true} if this queue contains no elements
     */
    public boolean isEmpty() {
        return first == null;
    }


    private static class Node<T> {
        T ele;
        Node<T> prev;
        Node<T> next;

        Node(T ele, Node<T> prev, Node<T> next) {
            this.ele = ele;
            this.prev = prev;
            this.next = next;
        }
    }
}

