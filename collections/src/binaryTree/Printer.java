package binaryTree;

import java.util.ArrayList;
import java.util.List;

public class Printer {


    public static <T extends Comparable<?>> void print(BinaryTree.Node<T> root) {
        int maxLevel = Printer.maxLevel(root);

        printNodeInternal(List.of(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<BinaryTree.Node<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || Printer.isAllElementsNull(nodes)){
            return;
        }

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        Printer.printWhitespaces(firstSpaces);

        List<BinaryTree.Node<T>> newNodes = new ArrayList<BinaryTree.Node<T>>();
        for (BinaryTree.Node<T> node : nodes) {
            if (node != null) {
                System.out.print(node.key);
                newNodes.add(node.leftChild);
                newNodes.add(node.rightChild);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            Printer.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                Printer.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    Printer.printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).leftChild != null)
                    System.out.print("/");
                else
                    Printer.printWhitespaces(1);

                Printer.printWhitespaces(i + i - 1);

                if (nodes.get(j).rightChild != null)
                    System.out.print("\\");
                else
                    Printer.printWhitespaces(1);

                Printer.printWhitespaces(edgeLines + edgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(BinaryTree.Node<T> node) {
        if (node == null)
            return 0;

        return Math.max(Printer.maxLevel(node.leftChild), Printer.maxLevel(node.rightChild)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }
        return true;
    }
}
