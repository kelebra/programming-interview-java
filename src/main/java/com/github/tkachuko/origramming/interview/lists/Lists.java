package com.github.tkachuko.origramming.interview.lists;

import java.util.LinkedList;
import java.util.List;

public class Lists {

    /**
     * Creates linked list from given values
     *
     * @param elements values in form of array
     * @param <T>      type of elements
     * @return linked list out of elements
     */
    public static <T> Node<T> from(List<T> elements) {
        if (elements == null || elements.size() == 0) return null;

        Node<T> head = new Node<>(elements.get(0));
        Node<T> current = head;
        for (int i = 1; i < elements.size(); i++) {
            current.tail = new Node<>(elements.get(i));
            current = current.tail;
        }
        return head;
    }

    /**
     * Converts linked list to java list
     *
     * @param head of linked list
     * @param <T>  type of elements in list
     * @return java collection
     */
    public static <T> List<T> toJavaList(Node<T> head) {
        List<T> list = new LinkedList<>();
        while (head != null) {
            list.add(head.data);
            head = head.tail;
        }
        return list;
    }

    /**
     * Merges two sorted linked lists into a sorted linked list
     *
     * @param first  sorted list
     * @param second sorted list
     * @return single sorted linked list
     */
    public static Node<Integer> sortedFromTwoSorted(Node<Integer> first, Node<Integer> second) {
        Node<Integer> dummy = new Node<>(0);
        Node<Integer> current = dummy;

        while (first != null && second != null) {
            if (first.data < second.data) {
                current.tail = first;
                first = first.tail;
            } else {
                current.tail = second;
                second = second.tail;
            }
            current = current.tail;
        }

        current.tail = (first != null) ? first : second;

        return dummy.tail;
    }

    /**
     * Reverses linked list (recursively)
     *
     * @param head of linked list
     * @param <T>  type of elements
     * @return reversed linked list
     */
    public static <T> Node<T> reverseRecursively(Node<T> head) {
        if (head == null) return null;
        if (head.tail == null) return head;

        Node<T> reversed = reverseRecursively(head.tail);

        head.tail.tail = head;
        head.tail = null;

        return reversed;
    }

    /**
     * Reverses linked list (without recursion)
     *
     * @param head of linked list
     * @param <T>  type of elements
     * @return reversed linked list
     */
    public static <T> Node<T> reverseWithoutRecursion(Node<T> head) {
        if (head == null) return null;
        else if (head.tail == null) return head;

        Node<T> previous = null, current = head;
        while (current != null) {
            Node<T> originalTail = current.tail;
            current.tail = previous;
            previous = current;
            current = originalTail;
        }
        return previous;
    }

    /**
     * Reverses range in linked list
     *
     * @param head start of linked list
     * @param from element to start reverse (starts from 1)
     * @param to   element to finish reverse
     * @param <T>  type of element ins list
     * @return linked list where elements starting from given indexes are reversed
     */
    public static <T> Node<T> reverseRange(Node<T> head, int from, int to) {
        boolean headIsGoingToChange = from > 1;
        Node<T> current = head;
        int counter = 1;
        Node<T> startOfSubList;

        if (headIsGoingToChange) {
            while (counter++ != from - 1) {
                current = current.tail;
            }
            startOfSubList = current.tail;
        } else {
            startOfSubList = head;
        }

        Node<T> previous = null, currentInReversal = startOfSubList;
        while (counter++ <= to) {
            Node<T> tmp = currentInReversal.tail;
            currentInReversal.tail = previous;
            previous = currentInReversal;
            currentInReversal = tmp;
        }

        if (headIsGoingToChange) {
            head.tail = current;
            current.tail = previous;
            startOfSubList.tail = currentInReversal;
            return head;
        } else {
            current.tail = currentInReversal;
            return previous;
        }
    }

    /**
     * Determines if list has a cycle and returns start of the cycle or null if no cycle detected
     *
     * @param head list
     * @param <T>  type of elements
     * @return start of cylec or null if no cycles in list
     */
    public static <T> Node<T> cycleStart(Node<T> head) {
        Node<T> slow = head;
        Node<T> fast = head.tail;

        while (slow != fast) {
            if (slow.tail == null || fast == null || fast.tail == null) return null;
            slow = slow.tail;
            fast = fast.tail.tail;
        }

        int cycleLength = 1;
        slow = slow.tail;
        while (slow != fast) {
            slow = slow.tail;
            cycleLength++;
        }

        Node<T> distant = head;
        for (int i = 0; i < cycleLength; i++) {
            distant = distant.tail;
        }

        while (distant != head) {
            distant = distant.tail;
            head = head.tail;
        }

        return head;
    }
}
