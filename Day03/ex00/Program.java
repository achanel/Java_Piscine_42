package ex00;

public class Program implements Runnable{
    private static int number;
    final int count;
    final String string;

    public Program(int number, String s) {
        this.count = number;
        this.string = s;
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.err.println("Bad argument!");
            System.exit(-1);
        }
        String numStr = args[0].substring(8);
        try {
            number = Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            System.out.println("Bad argument!");
            System.exit(-1);
        }

        Thread eggThread = new Thread(new Program(number, "Egg"));
        Thread henThread = new Thread(new Program(number, "Hen"));

        eggThread.start();
        henThread.start();

        eggThread.join();
        henThread.join();

        for (int i = 0; i < number; i++) {
            System.out.println("Human");
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(string);
        }
    }
}
