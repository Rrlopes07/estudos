import printer.PrintVector;
import sorts.BubSort;
import sorts.SelecSort;

public class Main {
    public static void main(String[] args) {
        int[] vector = {3,6,8,1,4,9,0};
        int[] vector2 = {3,6,8,1,4,9,0};

        SelecSort select1 = new SelecSort();
        BubSort select2 = new BubSort();
        PrintVector i = new PrintVector();

        i.printVector(select1.doSelectionSort(vector));
        System.out.println();
        i.printVector(select2.doBubbleSort(vector2));
    }
}