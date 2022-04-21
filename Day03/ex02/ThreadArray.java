package ex02;

public class ThreadArray extends Thread {
    int start;
    int end;
    int steps;
    int[] array;
    long threadsSum;


    public ThreadArray(int start, int steps, int end, int[] array) {
        this.start = start;
        this.end = end;
        this.steps = steps;
        this.array = array;
        threadsSum = 0;
    }

    public long getThreadsSum() {
        return threadsSum;
    }

    @Override
    public void run() {
        for (int i = start * steps; i <= end; i++) {
            threadsSum += array[i];
        }
        System.out.println("Thread " + (start + 1) + ": from " + start * steps + " to " + end + " sum is " + threadsSum);
    }
}
