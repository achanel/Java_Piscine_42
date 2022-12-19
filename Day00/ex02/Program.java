package ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int	coffee = 0;

        while (number != 42) {
            if (number < 2) {
                System.err.println("Error: Bad Argument, try again");
            }
            else if (isPrime(sumOfDigits(number))) {
                coffee++;
            }
            number = scanner.nextInt();
        }
        System.out.println("Count of coffee-request – " + coffee);
    }

    public static boolean isPrime(int number) {
        boolean PrimeStatus = true;
        int l = 0;
        int r = number;
        int m;
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
            if (number % i == 0) {
                PrimeStatus = false;
                break ;
            }
        }
        return PrimeStatus;
    }

    public static int sumOfDigits(int number) {
        int	res = 0;

        for(;;) {
            res += number % 10;
            number /= 10;
            if (number == 0) break;
        }
        return res;
    }
}
