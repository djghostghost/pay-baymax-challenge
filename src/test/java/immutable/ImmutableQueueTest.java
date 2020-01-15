package immutable;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ImmutableQueueTest {

    private static final String TEST_STRING_PAYPAY = "PayPay";
    private static final String[] TEST_ARRAY = new String[]{"paypay", "baxmax", "challenge", "come"};


    @Test
    public void isEmpty_empty_queue_return_true() {
        ImmutableQueue<Integer> queue = new ImmutableQueue<>();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void isEmpty_nonempty_queue_return_false() {
        ImmutableQueue<Integer> queue = new ImmutableQueue<>(3);
        assertFalse(queue.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void head_empty_queue() {
        ImmutableQueue<Integer> queue = new ImmutableQueue<>();
        assertTrue(queue.isEmpty());
        queue.head();
    }

    @Test
    public void head_nonempty_one_element_queue() {
        ImmutableQueue<String> queue = new ImmutableQueue<>(TEST_STRING_PAYPAY);
        assertFalse(queue.isEmpty());
        assertSame(queue.head(), TEST_STRING_PAYPAY);
    }

    @Test(expected = NoSuchElementException.class)
    public void tail_empty_queue() {
        ImmutableQueue<Integer> queue = new ImmutableQueue<>();
        assertTrue(queue.isEmpty());
        queue.tail();
    }

    @Test
    public void tail_nonempty_one_element_queue() {
        ImmutableQueue<String> queue = new ImmutableQueue<>(TEST_STRING_PAYPAY);
        assertFalse(queue.isEmpty());
        assertSame(queue.tail(), TEST_STRING_PAYPAY);
    }

    @Test(expected = NoSuchElementException.class)
    public void dequeue_empty_queue() {
        ImmutableQueue<Integer> queue = new ImmutableQueue<>();
        assertTrue(queue.isEmpty());
        queue.deQueue();
    }

    @Test
    public void dequeue_non_empty_queue() {
        ImmutableQueue<String> queue = new ImmutableQueue<>(TEST_STRING_PAYPAY);
        assertFalse(queue.isEmpty());

        ImmutableQueue<String> newQueue = queue.deQueue();

        assertNotSame(queue, newQueue);
        assertTrue(newQueue.isEmpty());
    }


    @Test(expected = IllegalArgumentException.class)
    public void enQueue_empty_queue_null_element() {
        ImmutableQueue<Integer> queue = new ImmutableQueue<>();
        assertTrue(queue.isEmpty());
        queue.enQueue(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enQueue_nonempty_queue_null_element() {
        ImmutableQueue<Integer> queue = new ImmutableQueue<>(3);
        assertFalse(queue.isEmpty());
        queue.enQueue(null);
    }

    @Test
    public void enQueue_empty_queue_normal_element() {
        ImmutableQueue<String> queue = new ImmutableQueue<>();
        assertTrue(queue.isEmpty());
        ImmutableQueue<String> queue1 = queue.enQueue(TEST_STRING_PAYPAY);
        assertNotSame(queue, queue1);
        assertTrue(queue.isEmpty());
        assertFalse(queue1.isEmpty());
        assertSame(TEST_STRING_PAYPAY, queue1.head());
        assertSame(TEST_STRING_PAYPAY, queue1.tail());
    }

    @Test
    public void enQueue_non_empty_one_element_queue_normal_element() {
        ImmutableQueue<String> queue = new ImmutableQueue<>(TEST_STRING_PAYPAY);
        assertFalse(queue.isEmpty());
        assertEquals(queue.head(), TEST_STRING_PAYPAY);
        assertEquals(queue.tail(), TEST_STRING_PAYPAY);

        //enqueue
        String testString = "bay";
        ImmutableQueue<String> newQueue = queue.enQueue(testString);
        assertFalse(newQueue.isEmpty());
        assertNotSame(queue, newQueue);
        assertSame(newQueue.head(), TEST_STRING_PAYPAY);
        assertSame(newQueue.tail(), testString);

        //original queue did not modified
        assertFalse(queue.isEmpty());
        assertSame(queue.head(), TEST_STRING_PAYPAY);
        assertSame(queue.tail(), TEST_STRING_PAYPAY);
    }

    @Test
    public void enQueue_empty_queue_multi_times() {
        ImmutableQueue<String> queue = new ImmutableQueue<>();
        assertTrue(queue.isEmpty());

        for (int i = 0; i < TEST_ARRAY.length; i++) {
            ImmutableQueue<String> originalQueue = queue;
            queue = queue.enQueue(TEST_ARRAY[i]);
            assertNotSame(queue, originalQueue);
            assertSame(queue.head(), TEST_ARRAY[0]);
            assertSame(queue.tail(), TEST_ARRAY[i]);
        }
    }

    @Test
    public void enQueue_nonempty_queue_multi_times() {
        ImmutableQueue<String> queue = new ImmutableQueue<>(TEST_STRING_PAYPAY);
        assertFalse(queue.isEmpty());
        assertSame(queue.head(), TEST_STRING_PAYPAY);
        assertSame(queue.tail(), TEST_STRING_PAYPAY);

        for (int i = 0; i < TEST_ARRAY.length; i++) {
            ImmutableQueue<String> originalQueue = queue;
            assertSame(originalQueue.head(), TEST_STRING_PAYPAY);
            queue = queue.enQueue(TEST_ARRAY[i]);
            assertNotSame(queue, originalQueue);
            assertSame(queue.head(), TEST_STRING_PAYPAY);
            assertSame(queue.tail(), TEST_ARRAY[i]);
        }
    }

    @Test
    public void deQueue_multi_times_empty_queue() {
        ImmutableQueue<String> queue = new ImmutableQueue<>();
        assertTrue(queue.isEmpty());
        //begin to enqueue
        for (int i = 0; i < TEST_ARRAY.length; i++) {
            queue = queue.enQueue(TEST_ARRAY[i]);
        }

        assertFalse(queue.isEmpty());
        assertSame(queue.head(), TEST_ARRAY[0]);
        assertSame(queue.tail(), TEST_ARRAY[TEST_ARRAY.length - 1]);

        //begin to dequeue

        for (int i = 0; i < TEST_ARRAY.length; i++) {
            ImmutableQueue<String> originalQueue = queue;
            assertSame(originalQueue.head(), TEST_ARRAY[i]);
            assertSame(originalQueue.tail(), TEST_ARRAY[TEST_ARRAY.length - 1]);
            queue = queue.deQueue();
            assertNotSame(queue, originalQueue);
        }
        assertTrue(queue.isEmpty());
    }

    @Test
    public void deQueue_multi_times_nonempty_queue() {
        ImmutableQueue<String> queue = new ImmutableQueue<>(TEST_STRING_PAYPAY);
        assertFalse(queue.isEmpty());
        //begin to enqueue
        for (int i = 0; i < TEST_ARRAY.length; i++) {
            queue = queue.enQueue(TEST_ARRAY[i]);
        }
        assertFalse(queue.isEmpty());
        assertSame(queue.head(), TEST_STRING_PAYPAY);
        assertSame(queue.tail(), TEST_ARRAY[TEST_ARRAY.length - 1]);

        //begin to dequeue

        for (int i = 0; i <= TEST_ARRAY.length; i++) {


            ImmutableQueue<String> originalQueue = queue;
            if (i == 0) {
                assertSame(originalQueue.head(), TEST_STRING_PAYPAY);
            } else {
                assertSame(originalQueue.head(), TEST_ARRAY[i - 1]);
            }
            assertSame(originalQueue.tail(), TEST_ARRAY[TEST_ARRAY.length - 1]);
            queue = queue.deQueue();
            assertNotSame(queue, originalQueue);
        }
        assertTrue(queue.isEmpty());
    }

    @Test
    public void buildNewQueue_based_another_nonempty_queue() {
        ImmutableQueue<String> queue = new ImmutableQueue<>();
        assertTrue(queue.isEmpty());
        //begin to enqueue
        for (int i = 0; i < TEST_ARRAY.length; i++) {
            queue = queue.enQueue(TEST_ARRAY[i]);
        }

        assertFalse(queue.isEmpty());
        ImmutableQueue<String> newQueue = new ImmutableQueue<>(queue);
        assertNotSame(newQueue, queue);
        assertFalse(newQueue.isEmpty());
        assertEquals(newQueue.head(), queue.head());
        assertEquals(newQueue.tail(), queue.tail());
        for (int i = 0; i < TEST_ARRAY.length; i++) {
            ImmutableQueue<String> originalQueue = queue;
            assertSame(originalQueue.head(), TEST_ARRAY[i]);
            assertSame(originalQueue.tail(), TEST_ARRAY[TEST_ARRAY.length - 1]);
            queue = queue.deQueue();
            assertNotSame(queue, originalQueue);
        }
    }

}