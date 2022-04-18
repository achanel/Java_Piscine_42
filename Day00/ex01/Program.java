package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int	status = 0;
        boolean PrimeStatus = true;
        int	steps = 0;
        if (number < 2) {
            System.err.println("Illegal Argument");
            status = -1;
        }
        else if (number == 2){
            System.out.println(PrimeStatus + " " + 1);
        }
        else {
            int l = 0;
            int r = number;
            int m = (l + r) / 2;
            int t = number;
            for(;;) {
                m = (l + r) / 2;
                if (m == t || m * m == number)
                    break ;
                if (m * m < number)
                    l = m;
                else
                    r = m;
                t = m;
            }
            for (int i = 2; i <= m; i++) {
                steps++;
                if (number % i == 0) {
                    PrimeStatus = false;
                    break ;
                }
            }
            System.out.println(PrimeStatus + " " + steps);
        }
        System.exit(status);
    }
}
