package linkedList.bidirLinkedList;

import java.util.Arrays;

public class LinkedList<E> {
    int size;
    private Node<E> first;

    static class Node<E>{
        E value;
        Node<E> prev;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }

    }

    public void addFirst(E element){
        Node<E> newNode = new Node<>(element);
        newNode.next = first;
        newNode.prev = null;
        first = newNode;
        size++;
    }

    public void add(E element){
        if(size == 0){
            addFirst(element);
            return;
        }
        Node<E> newNode = new Node<>(element);
        Node<E> currentNode = first;
        while (currentNode.next != null){
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
        newNode.prev = currentNode;
        newNode.next = null;
        size++;
    }

    public void add(E element, int index){
        isCheckIndex(index);
        if(index == 0){
            addFirst(element);
            return;
        }
        if(index == size){
            addLast(element);
            return;
        }
        Node<E> newNode = new Node<>(element);
        Node<E> prevNode = first;
        Node<E> currentNode = first;
        int currentIndex = 0;
        while (currentIndex != index){
            prevNode = currentNode;
            currentNode = currentNode.next;
            currentIndex++;
        }
        prevNode.next = newNode;
        newNode.next = currentNode;
        newNode.prev = prevNode;
        currentNode.prev = newNode;
        size++;
    }

    public void addLast(E element){
        Node<E> newNode = new Node<>(element);
        Node<E> currentNode = first;
        while (currentNode.next != null){
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
        newNode.prev = currentNode;
        newNode.next = null;
        size++;
    }

    public boolean deleteFirst(){
        if(size == 0){
            return false;
        }
        first = first.next;
        first.prev = null;
        size--;
        return true;
    }

    public boolean delete(int index){
        if(isCorrectIndex(index)){
            return false;
        }
        if(index == size - 1){
            return deleteLast();
        }
        Node<E> prevNode = first;
        Node<E> currentNode = first;
        int currentIndex = 0;
        while (currentIndex != index){
            prevNode = currentNode;
            currentNode = currentNode.next;
            currentIndex++;
        }
        prevNode.next = currentNode.next;
        currentNode.next.prev = prevNode;
        size--;
        return true;
    }

    public boolean deleteLast(){
        if(size == 0){
            return false;
        }
        Node<E> prevNode = first;
        Node<E> currentNode = first;
        while (currentNode.next != null){
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        prevNode.next = null;
        size--;
        return true;
    }

    public boolean remove(E element) {
        int index = indexOf(element);
        if(index >= 0){
            return delete(index);
        }
        return false;
    }

    public boolean contains(E element){
        return indexOf(element) >= 0;
    }

    public int indexOf(E element){
        int index = 0;
        if(element == null){
            for (Node<E> currentNode = first; currentNode != null; currentNode = currentNode.next) {
                if(currentNode.value == null){
                    return index;
                }
                index++;
            }
        }else {
            for (Node<E> currentNode = first; currentNode != null; currentNode = currentNode.next) {
                if(element.equals(currentNode.value)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public void sort() {
        Object[] a = this.toArray();
        Arrays.sort(a);
        Node<E> currentNode = first;
        int index = 0;
        while (currentNode != null){
            currentNode.value = (E) a[index];
            currentNode = currentNode.next;
            index++;
        }
    }

    private Object[] toArray() {
        Object[] a = new Object[size];
        Node<E> currentNode = first;
        int index = 0;
        while (currentNode != null){
            a[index] = currentNode.value;
            currentNode = currentNode.next;
            index++;
        }
        return a;
    }

    private void isCheckIndex(int index) {
        if(index < 0 || index > size){
            throw new RuntimeException("Index is not correct");
        }
    }

    private boolean isCorrectIndex(int index) {
        return index < 0 || index >= size;
    }

    @Override
    public String toString() {
        StringBuilder strList = new StringBuilder();
        Node<E> currentNode = first;
        if(size == 0){
            return "";
        }
        strList.append(currentNode.value);
        while (currentNode.next != null){
            currentNode = currentNode.next;
            strList.append(" ").append(currentNode.value);
        }
        return strList.toString();
    }
}
