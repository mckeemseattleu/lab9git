package mckee_lab9x;

public class Queue<T> {

    private Node head; // stores the head node (top of the queue)
    private Node tail; // stores the tail node (end of the queue)
    private int size; // keeps track of how many nodes are on the queue

    /**
     * Constructor - initializes head and tail to null
     */
    public Queue() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds a node with the provided value to the end of the queue
     *
     * @param value the value to store in the node
     */
    public void enqueue(T value) {

        Node newNode = new Node(value); // the new node to add

        // increment the size counter
        size++;

        // if the queue is empty, the new node is both head and tail
        if (empty()) {
            head = newNode;
            tail = newNode;

            // otherwise, the node is added to the end of the queue
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    /**
     * Removes an item from the queue and returns its value
     *
     * @return the value of the node at the front of the queue
     * @throws EmptyQueueException if the queue is empty
     */
    public T dequeue() {

        // if the queue is empty, there is nothing to dequeue so throw an error
        if(empty()) {
            throw new EmptyQueueException();
        }

        // decrement the size counter
        size--;

        // store the value to return
        T rc = head.value;

        // remove the head node by setting the head reference to the
        // subsequent node
        head = head.next;

        // if there were no more nodes, head will be null and tail should be too
        if(head == null) {
            tail = null;

            // otherwise clean up the prev reference in our new head node
        } else {
            head.prev = null;
        }

        return rc;

    }

    /**
     * Returns the value of the node at the front of the queue without
     * removing it
     *
     * @return the value of the node at the front of the queue
     * @throws EmptyQueueException if the queue is empty
     */
    public T peek() {
        if(empty()) {
            throw new EmptyQueueException();
        }

        return head.value;
    }

    /**
     * Returns the content of the queue in string format
     *
     * @return a string the content of the queue
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(); // to build a string

        // if there are nodes in the queue
        if(!empty()) {

            // start at the head
            Node curNode = head;

            // traverse the queue
            while(curNode != null) {

                sb.append(curNode.value).append(" ");
                curNode = curNode.next;
            }

            // if the queue is empty, we say so
        } else {
            sb.append("Empty Queue");
        }

        return sb.toString();
    }


    /**
     * Appends the provided queue to the end of this queue.
     *
     * @param queue the queue to append to this queue
     */
    public void append(Queue<T> queue) {

        // set a placeholder that starts at the head of the provided queue
        Node curNode = queue.head;

        // traverse the provided queue without adjusting it and enqueue its
        // values in this queue
        while (curNode != null) {
            enqueue(curNode.value);
            curNode = curNode.next;
        }

    }

    /**
     * Makes a copy of this queue and all of its nodes
     *
     * @return a copy of the queue
     */
    public Queue<T> copy() {
        Queue<T> newQueue = new Queue<>(); // the new queue

        // if our queue isn't empty
        if(!empty()) {

            // start at the head
            Node curNode = this.head;
            while(curNode != null) {

                // and enqueue each node into the new queue
                newQueue.enqueue(curNode.value);
                curNode = curNode.next;
            }
        }

        // return the new queue
        return newQueue;
    }

    /**
     * Checks if this queue has the same content as the passed queue.
     *
     * @param compareQueue queue to compare with this queue
     * @return true if the same, false if different
     */
    public boolean equals(Queue<T> compareQueue) {

        Node curNode = head; // placeholder for traversing our queue
        Node compareCurNode = compareQueue.head; // for traversing new queue

        // if they're not the same size, they aren't equal
        if(this.size() != compareQueue.size()) {
            return false;
        }

        // traverse each queue and compare the value of the nodes
        while(curNode != null && compareCurNode != null) {

            // if they don't match, return false
            if(!curNode.value.equals(compareCurNode.value)) {
                return false;
            }

            // advance to the next nodes
            curNode = curNode.next;
            compareCurNode = compareCurNode.next;
        }

        // return true because we made it this far w/o a false comparison
        return true;
    }

    /**
     * Checks if the queue is empty
     *
     * @return true if empty, otherwise false
     */
    public boolean empty() {
        return head == null;
    }

    /**
     * Returns the size of the queue
     *
     * @return number of nodes on the queue
     */
    public int size() {
        return size;
    }

    /**
     * This class defines the nodes in the linked list that make up the queue
     */
    private class Node {
        T value;
        Node next;
        Node prev;

        /**
         * Constructor - initializes node with the value provided
         *
         * @param value the value of this node's payload
         */
        public Node(T value) {
            this.value = value;
            next = null;
            prev = null;
        }

    }

}

/**
 * Exception to throw if we try to dequeue the queue while its empty
 */
class EmptyQueueException extends RuntimeException
{
}