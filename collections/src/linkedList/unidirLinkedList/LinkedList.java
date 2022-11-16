package linkedList.unidirLinkedList;

public class LinkedList<E> {
    private Node first;
    int size;

    class Node {
        E value;  //значение
        Node next;  ///ссылка на следующую ячейку

        public Node(E value) {
            this.value = value;
        }
    }

    public void add(E value) {
        if (size == 0) {
            addFirst(value);
        }
        Node newNode = new Node(value);
        if (size == 0) {
            first = newNode;
            first.next = null;
            size++;
            return;
        }
        Node current = first;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        size++;
    }

    public void add(E value, int index) {
        if (!isCheckIndex(index)) {
            return;
        }
        if (size == 0) {
            addFirst(value);
        }
        Node currentNode = first;
        int currentIndex = 0;

        Node newNode = new Node(value);

        while (currentIndex + 1 != index) {
            currentNode = currentNode.next;
            currentIndex++;
        }
        newNode.next = currentNode.next;
        currentNode.next = newNode;
        size++;
    }

    public void addFirst(E value) {
        if (size != 0) {
            add(value);
        }
        Node newNode = new Node(value);
        first = newNode;
        size++;

    }

    public boolean delete() {
        if (size == 0) {
            return false;
        }
        Node prevNode = first;
        Node currentNode = first;
        while (currentNode.next != null) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        prevNode.next = null;
        size--;
        return true;
    }

    public boolean delete(int index) {
        if (!isCheckIndex(index)) {
            return false;
        }
        Node prevNode = first;
        Node currentNode = first;
        int currentIndex = 0;
        while (currentIndex != index) {
            prevNode = currentNode;
            currentNode = currentNode.next;
            currentIndex++;
        }
        prevNode.next = currentNode.next;
        size--;
        return true;
    }

    public boolean contains(E value) {
        Node currentNode = first;
        while (currentNode != null) {
            if (currentNode.value == value) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    private E contains(int index){
        exceptionCheckIndex(index);
        Node currentNode = first;
        int currentIndex = 0;
        while (currentIndex != index){
            currentNode = currentNode.next;
            currentIndex++;
        }
        return currentNode.value;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (size != 0) {
            Node currentNode = first;
            str.append(currentNode.value);
            while (currentNode.next != null) {
                str.append(" ").append(currentNode.next.value);
                currentNode = currentNode.next;
            }
            str.append(System.lineSeparator());
        }
        return str.toString();
    }

    private boolean isCheckIndex(int index) {
        if (index < 0 && index > size) {
            System.out.println("Index is not correct");
            return false;
        }
        return true;
    }

    private void exceptionCheckIndex(int index) {
        if (index < 0 && index > size) {
            throw new IndexOutOfBoundsException("Index is not correct");
        }
    }

}


