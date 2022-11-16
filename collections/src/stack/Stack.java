package stack;

import java.util.Arrays;

public class Stack<E> {
    private static Object[] stack;
    private static final int initialSize = 10;
    int size;

    public Stack() {
        stack = new Object[initialSize];
    }

    public void push(E element) {
        addElement(element);
    }

    public E pop() {
        return deleteElement();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (size == 0) {
            return null;
        }
        return (E) stack[size - 1];
    }

    private void addElement(E element) {
        if (size + 1 > stack.length) {
            growStack();
        }
        stack[size] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    private E deleteElement() {
        isEmptyStack();
        size--;
        return (E) stack[size];
    }

    private void growStack() {
        size = (int) (size * 1.5) + size;
        Object[] newStack = new Object[size];
        for (int i = 0; i < stack.length; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
    }

    private void isEmptyStack() {
        if (size == 0) {
            throw new RuntimeException("Stack is Empty");
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Arrays.stream(stack).forEach(element -> str.append(element).append(" "));
        return str.toString();
    }
}