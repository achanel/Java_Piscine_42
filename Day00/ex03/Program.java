package ex03;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.print("-> ");
        Scanner	scanner = new Scanner(System.in);
        String	week = scanner.nextLine();
        int		weekNum = 1;
        long	gradeArr = 0;

        while (weekNum <= 18 && !week.equals("42")) {
            if (weekNum > 1)
                checkOrder(week, weekNum);
            gradeArr = pushArr(minGrade(scanner), gradeArr, weekNum);
            System.out.print("-> ");
            week = scanner.nextLine();
            weekNum++;
        }
        for (int i = 1; i < weekNum; i++) {
            System.out.print("Week " + i + " ");
            gradeLength(pullNum(gradeArr, i));
        }
    }

    public static void gradeLength(int number) {
        for (int i = 0; i < number; i++)
            System.out.print("=");
        System.out.println(">");
    }

    public static long pushArr(int min, long gradeArr, int weekNum) {
        long ten = 1;

        for(int i = 1; i < weekNum; i++) {
            ten *= 10;
        }
        return (gradeArr + (min * ten));
    }

    public static int pullNum(long gradeArr, int weekNum) {
        int res;

        for (int i = 1; i < weekNum; i++) {
            gradeArr /= 10;
        }
        res = (int)(gradeArr % 10);
        return res;
    }

    public static int minGrade(Scanner scanner)
    {
        int min = 9;
        int number;

        System.out.print("-> ");
        for(int i = 0; i < 4; i++) {
            number = scanner.nextInt();
            if (number < min)
                min = number;
        }
        scanner.nextLine();
        return min;
    }

    public static void checkOrder(String week, int weekNum) {
        if (!week.equals("Week " + (weekNum)))
            errorExit("IllegalArgument");
    }

    public static void errorExit(String error) {
        System.err.println(error);
        System.exit(-1);
    }
}
