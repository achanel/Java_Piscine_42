package ex02;

import java.util.Random;

public class Program {

    public static void main(String[] args) {
        int arraySize;
        int threadsCount;
        int sum = 0;
        int threadsSum = 0;

        if (args.length != 2) {
            System.err.println("Bad argument!");
            System.exit(-1);
        }
        arraySize = getASize(args);
        threadsCount = getTCount(args);
        if (arraySize <= threadsCount) {
            System.err.println("Bad argument!");
            System.exit(-1);
        }
        int[] array = new int[arraySize];
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(1000);
        }
        for (int i = 0; i < arraySize; i++) {
            sum += array[i];
        }
        System.out.println("Sum: " + sum);
        ThreadArray []threadArrays = new ThreadArray[threadsCount];
        int steps = arraySize/threadsCount + 1;
        for (int i = 0; i < threadsCount; i++) {
            if ((threadsCount - 1) > i) {
                int notLast = (i + 1) * steps - 1;
                threadArrays[i] = new ThreadArray(i, steps, notLast, array);
            }
            else {
                threadArrays[i] = new ThreadArray(i, steps, arraySize - 1, array);
            }
            threadArrays[i].start();
        }
        for (int i = 0; i < threadsCount; i++) {
            try {
                threadArrays[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < threadsCount; i++) {
            threadsSum += threadArrays[i].getThreadsSum();
        }
        System.out.println("Sum by threads: " + threadsSum);
    }

    public static int getASize(String[] args) {
        if (!args[0].startsWith("--arraySize=")) {
            System.err.println("Bad argument!");
            System.exit(-1);
        }
        String strings = args[0].substring(12);
        int res = Integer.parseInt(strings);
        if (res > 2000000) {
            System.err.println("Bad argument!");
            System.exit(-1);
        }
        return res;
    }

    public static int getTCount(String[] args) {
        if (!args[1].startsWith("--threadsCount=")) {
            System.err.println("Bad argument!");
            System.exit(-1);
        }
        String strings = args[1].substring(15);
        try {
            return (Integer.parseInt(strings));
        } catch (NumberFormatException e) {
            System.err.println("Bad argument!");
            System.exit(-1);
        }
        return 1;
    }
}
