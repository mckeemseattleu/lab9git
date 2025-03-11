package mckee_lab9x;
import static org.junit.Assert.*;

import org.junit.Test;

public class QueueTest {

    /**
     * This test validates the constructor for Queue
     */
    @Test
    public void testQueue() {
        Queue<Double> queueDouble = new Queue<Double>();
        Queue<String> queueString = new Queue<String>();

        assertNotNull(queueDouble);
        assertNotNull(queueString);
    }

    /**
     * This test validates the enqueue and peek methods for Queue
     *
     */
    @Test
    public void testEnqueueAndPeek() {
        Queue<Double> queueDouble = new Queue<Double>();
        Queue<String> queueString = new Queue<String>();

        //Validate additions to empty queue.
        queueDouble.enqueue(1.0);
        queueString.enqueue("Value 1");

        assertEquals(1.0, queueDouble.peek(), 0);
        assertEquals("Value 1", queueString.peek());


        //Validate additions to populated queues.
        queueDouble.enqueue(2.0);
        queueString.enqueue("Value 2");

        assertEquals(1.0, queueDouble.peek(), 0);
        assertEquals("Value 1", queueString.peek());
    }

    /**
     * This test validates the Dequeue method of Queue
     *
     */
    @Test
    public void testDequeue() {
        Queue<Double> queueDouble = new Queue<Double>();
        Queue<String> queueString = new Queue<String>();

        queueDouble.enqueue(1.0);
        queueString.enqueue("Value 1");
        queueDouble.enqueue(2.0);
        queueString.enqueue("Value 2");

        //Check for a queue with N members
        assertEquals(1.0, queueDouble.dequeue(), 0);
        assertEquals("Value 1", queueString.dequeue());

        //Check for a queue with one member
        assertEquals(2.0, queueDouble.dequeue(), 0);
        assertEquals("Value 2", queueString.dequeue());

        //Check for an empty queue
        assertNull(queueDouble.dequeue());
        assertNull(queueString.dequeue());
    }

    /**
     * This test validates that the toString method for queue works.
     *
     */
    @Test
    public void testToString() {
        Queue<Double> queueDouble = new Queue<Double>();
        Queue<String> queueString = new Queue<String>();

        queueDouble.enqueue(1.0);
        queueString.enqueue("Value 1");
        queueDouble.enqueue(2.0);
        queueString.enqueue("Value 2");

        assertEquals("1.0\n2.0", queueDouble.toString());
        assertEquals("Value 1\nValue 2", queueString.toString());
    }

    /**
     * This test validates that the copy method for queue completes a deep copy
     *
     */
    @Test
    public void testCopy() {
        Queue<Double> queueDouble = new Queue<Double>();
        Queue<String> queueString = new Queue<String>();

        queueDouble.enqueue(1.0);
        queueString.enqueue("Value 1");
        queueDouble.enqueue(2.0);
        queueString.enqueue("Value 2");

        Queue<Double> queueDouble2 = queueDouble.copy();
        Queue<String> queueString2 = queueString.copy();

        //Ensure the objects are distinct
        assertNotSame(queueDouble2, queueDouble);
        assertNotSame(queueString2, queueString);

        //Assert the values are equivalent
        assertTrue(queueDouble2.equals(queueDouble));
        assertTrue(queueString2.equals(queueString));
    }

    /**
     * This test validates the full functionality of the equals method of queue.
     *
     */
    @Test
    public void testEquals() {
        Queue<Double> queueDouble = new Queue<Double>();
        Queue<String> queueString = new Queue<String>();

        Queue<Double> queueDouble2 = queueDouble.copy();
        Queue<String> queueString2 = queueString.copy();

        //Test Both Queues Null
        assertTrue(queueDouble2.equals(queueDouble));
        assertTrue(queueString2.equals(queueString));

        //Test First Queue Null
        queueDouble2.enqueue(1.0);
        queueString2.enqueue("Value 1");

        assertFalse(queueDouble2.equals(queueDouble));
        assertFalse(queueString2.equals(queueString));

        //Test Second Queue Null
        queueDouble2.dequeue();
        queueString2.dequeue();
        queueDouble.enqueue(1.0);
        queueString.enqueue("Value 1");

        assertFalse(queueDouble2.equals(queueDouble));
        assertFalse(queueString2.equals(queueString));

        //Test Both Queues Populated
        queueDouble.enqueue(2.0);
        queueString.enqueue("Value 2");
        queueDouble2.enqueue(1.0);
        queueString2.enqueue("Value 1");
        queueDouble2.enqueue(2.0);
        queueString2.enqueue("Value 2");

        assertTrue(queueDouble2.equals(queueDouble));
        assertTrue(queueString2.equals(queueString));

        //Test First Queue Longer
        queueDouble = new Queue<Double>();
        queueString = new Queue<String>();
        queueDouble2 = new Queue<Double>();
        queueString2 = new Queue<String>();

        queueDouble.enqueue(1.0);
        queueString.enqueue("Value 1");
        queueDouble.enqueue(2.0);
        queueString.enqueue("Value 2");
        queueDouble2.enqueue(1.0);
        queueString2.enqueue("Value 1");

        assertFalse(queueDouble2.equals(queueDouble));
        assertFalse(queueString2.equals(queueString));

        //Test Second Queue Longer
        queueDouble2.enqueue(2.0);
        queueString2.enqueue("Value 2");
        queueDouble2.enqueue(3.0);
        queueString2.enqueue("Value 3");

        assertFalse(queueDouble2.equals(queueDouble));
        assertFalse(queueString2.equals(queueString));

        //Test Same Length but unequal values
        queueDouble.enqueue(3.1);
        queueString.enqueue("Value 3.1");

        assertFalse(queueDouble2.equals(queueDouble));
        assertFalse(queueString2.equals(queueString));

    }

    /**
     * This test validates that the Append method works as expected.
     *
     */
    @Test
    public void testAppend() {
        Queue<Double> queueDouble = new Queue<Double>();
        Queue<String> queueString = new Queue<String>();
        Queue<Double> queueDouble2 = new Queue<Double>();
        Queue<String> queueString2 = new Queue<String>();

        queueDouble.enqueue(1.0);
        queueString.enqueue("Value 1");
        queueDouble.enqueue(2.0);
        queueString.enqueue("Value 2");

        //Append The first Queue to the Second Queues Twice
        queueDouble2.append(queueDouble);
        queueDouble2.append(queueDouble);
        queueString2.append(queueString);
        queueString2.append(queueString);

        //Validate expected values are in second queues
        assertEquals("1.0\n2.0\n1.0\n2.0", queueDouble2.toString());
        assertEquals("Value 1\nValue 2\nValue 1\nValue 2", queueString2.toString());
    }


}
