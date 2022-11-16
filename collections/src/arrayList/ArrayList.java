package arrayList;

public class ArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static int capacity = DEFAULT_CAPACITY;
    private static Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    public int size(){
        return size;
    }

    public void add(E element){
        if(size + 1 == capacity){
            grow();
        }
        data[size] = element;
        size++;
    }

    public void add(E element, int index){
        if(size + 1 == capacity) {
            grow();
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i-1];
        }
        data[index] = element;
        size++;
    }

    public void addFirst(E element){
        if(size + 1 == capacity) {
            grow();
        }
        for (int i = size; i >=0; i--) {
            data[i] = data[i-1];
        }
        data[0] = element;
        size++;
    }

    public boolean contains(E element){
        if(indexOf(element) >= 0){
            return true;
        }
        return false;
    }

    public int indexOf(E element){
        for (int i = 0; i < data.length; i++) {
            if(element.equals(data[i])){
                return i;
            }
        }
        return -1;
    }

    public boolean delete(int index){
        for (int i = index; i < capacity; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return true;
    }

    public boolean deleteLast(){
        if(size != 0){
            size--;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public E get(int index){
        exceptionCheckIndex(index);
        return (E) data[index];
    }

    public void set(E newValue, int index){
        exceptionCheckIndex(index);
        data[index] = newValue;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public void printAllElements() {
        for (int i = 0; i < capacity; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    private void exceptionCheckIndex(int index) {
        if(index < 0 || index >= capacity) {
            throw new IndexOutOfBoundsException("Введен неверный индекс");
        }
    }

    private boolean isCheckIndex(int index) {
        return index >= 0 && index < capacity;
    }

    private void grow() {
        capacity = (int)(capacity * 1.5) + 1;
        Object[] newData = new Object[capacity];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }
}

