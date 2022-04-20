package ex01;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("user1", 10000);
        User user2 = new User("user2", 20000);
        User user3 = new User("user3", 30000);
        User user4 = new User("user4", 40000);
        User user5 = new User("user5", 50000);
        User user6 = new User("user6", 60000);
        User user7 = new User("user7", 70000);
        User user8 = new User("user8", 800000);
        User user9 = new User("user9", -10000);
        User user10 = new User("user10", 0);

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);
        System.out.println(user5);
        System.out.println(user6);
        System.out.println(user7);
        System.out.println(user8);
        System.out.println(user9);
        System.out.println(user10);
    }
}
