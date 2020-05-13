package bubble;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = new int[]{8, 3, 4, 7, 100, -100};

        bubbleSort(array);

        System.out.println(Arrays.toString(array));
    }

    public static void bubbleSort(int[] array) {
        boolean needToSort = true;
        int temp;
        while (needToSort) {
            needToSort = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    needToSort = true;
                }
            }
        }
    }
}
