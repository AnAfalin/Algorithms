package binaryTree;

public class BinaryTree<T extends Comparable<T>> {

    Node<T> root;       //корень дерева
    int size;

    static class Node<T> {
        T key;          //данные, используемые в качестве ключа
        Node<T> leftChild;     //левый потомок
        Node<T> rightChild;    //правый потомок

    }

    @SuppressWarnings("unchecked")
    public Node<T> find(T key){
        Node<T> current = root;
        while ((current.key != key)){
            if(key.compareTo(current.key) < 0){
                current = current.leftChild;
            }else {
                current = current.rightChild;
            }
            if(current == null){
                return null;
            }
        }
        return current;
    }

    public void insert(T key){
        Node<T> newNode = new Node<T>();
        newNode.key = key;
        if(root == null){
            root = newNode;
        }else {
            Node<T> current = root;
            Node<T> parent;
            while (true){
                parent = current;
                if(key.compareTo(current.key) < 0){
                    current = current.leftChild;
                    if(current == null){
                        parent.leftChild = newNode;
                        size++;
                        return;
                    }
                }else {
                    current = current.rightChild;
                    if(current == null){
                        parent.rightChild = newNode;
                        size++;
                        return;
                    }
                }
            }
        }
    }

    public Node<T> minimum(){
        if(root == null){
            return null;
        }
        Node<T> current = root;
        Node<T> last = current;
        while (current != null){
            last = current;
            current = current.leftChild;
        }
        return last;
    }

    public Node<T> maximum(){
        if(root == null){
            return null;
        }
        Node<T> current = root;
        Node<T> last = current;
        while (current != null){
            last = current;
            current = current.rightChild;
        }
        return last;
    }

    public boolean delete(T key){
        if(root == null){
            return false;
        }

        Node<T> current = root;
        Node<T> parent = root;
        boolean isLeftChild = true;
        while (current.key != key){             //пока ключ текущего узла не равен исходному
            parent = current;
            if(key.compareTo(current.key) < 0){              //если ключ меньше ключа текущего узла, то двигаемся налево
                isLeftChild = true;
                current = current.leftChild;
            }else {                             //иначе направо
                isLeftChild = false;
                current = current.rightChild;
            }
            if(current == null){                //если текущий узел равен null, то искомый ключ не найден
                return false;
            }
        }
        //если вышли из цикла, значит искомый ключ найден

        //если найденный узел не имеет потомков
        if(current.leftChild == null && current.rightChild == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
        }

        //если найденный узел имеет одного потомка
        //левый потомок
        else if (current.rightChild == null) {
            if(current == root){
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            }else {
                parent.rightChild = current.leftChild;
            }
        }

        //правый потомок
        else if (current.leftChild == null) {
            if(current == root){
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            }else {
                parent.rightChild = current.rightChild;
            }
        }

        //если найденный узел имеет двух потомков
        else {
            Node<T> successor = getSuccessor(current);

            if(current == root){
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            }else {
                parent.rightChild = successor;
            }
            successor.leftChild = current.leftChild;
        }
        size--;
        return true;
    }

    public void print(){
        inOrder(root);
    }

    public void printTree(){
        Printer.print(root);
    }

    private void inOrder(Node<T> localRoot){
        if(localRoot != null){
            inOrder(localRoot.leftChild);
            System.out.println(localRoot.key + " ");
            inOrder(localRoot.rightChild);
        }
    }

    private Node<T> getSuccessor(Node<T> delNode){
        Node<T> successorParent = delNode;
        Node<T> successor = delNode;
        Node<T> current = delNode.rightChild;
        while (current != null){
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        if(successor != delNode.rightChild){
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }

}
