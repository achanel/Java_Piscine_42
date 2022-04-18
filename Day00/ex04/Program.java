package ex04;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.length() == 0)
            System.exit(0);
        char[] inpArr = input.toCharArray();
        char[] charCount = new char[input.length()];
        int[] numCount = new int[input.length()];
        int letter = 0;
        int count = 0;
        for (int i = 0; i < inpArr.length; i++) {
            if ((letter = letterInStr(charCount, inpArr[i], count)) != -1) {
                numCount[letter]++;
            }
            else {
                charCount[count] = inpArr[i];
                numCount[count] = 1;
                count++;
            }
        }
        sortAll(charCount, numCount, count);
    }

    public static int letterInStr(char[] array, char letter, int index) {
        for (int i = 0; i < index; i++) {
            if (array[i] == letter) {
                return i;
            }
        }
        return -1;
    }

    public static void sortAll(char[] charCount, int[] numCount, int count) {
        boolean isSorted = false;
        boolean sortC;
        int i;
        int tmp_i;
        char tmp_c;
        while (!isSorted) {
            i = 0;
            sortC = false;
            while (i < count) {
                if (i + 1 != count && (numCount[i] < numCount[i + 1] ||
                        (numCount[i] == numCount[i + 1] && charCount[i] > charCount[i + 1]))) {
                    tmp_i = numCount[i];
                    numCount[i] = numCount[i + 1];
                    numCount[i + 1] = tmp_i;
                    tmp_c = charCount[i];
                    charCount[i] = charCount[i + 1];
                    charCount[i + 1] = tmp_c;
                    sortC = true;
                }
                else
                    i++;
            }
            if (!sortC) {
                isSorted = true;
            }
        }
        printAll(numCount, charCount, count);
    }

    public static void printAll(int[] numCount, char[] charCount, int count) {
        double floor = numCount[0] / 10.0;
        int[] pillar = new int[10];
        if (count > 10) {
            count = 10;
        }
        for (int i = 0; i < count; i++) {
            pillar[i] = (int)(numCount[i] / floor) + 1;
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < count; j++) {
                if (pillar[j] + i == 11) {
                    System.out.printf("%d\t", numCount[j]);
                }
                else if (pillar[j] + i > 11) {
                    System.out.printf("#\t");
                }
                else {
                    System.out.println("");
                    break;
                }
                if (j + 1 == count) {
                    System.out.println("");
                }
            }
        }
        for (int i = 0; i < count; i++) {
            System.out.printf("%c", charCount[i]);
            if (i + 1 != count)
                System.out.print("\t");
        }
    }
}
