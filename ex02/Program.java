package ex02;

public class Program {
    public static void main(String[] args) throws ThrowableClass {
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

        UsersList list = new UsersArrayList();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);
        list.add(user7);
        list.add(user8);
        list.add(user9);
        list.add(user10);

        System.out.println(list.retrieveById(4));
        System.out.println(list.retrieveByIndex(user8.getIdentifier()));
        System.out.println("-----------------------------------");
        System.out.println(list.retrieveByIndex(1));
        System.out.println(list.retrieveByIndex(user3.getIdentifier()));
        System.out.println("-----------------------------------");
        System.out.println(list.retrieveCount());
        User user11 = new User("user11", 3);
        list.add(user11);
        System.out.println(list.retrieveCount());
        System.out.println("-----------------------------------");
        System.out.println(list.retrieveById(-2));
    }
}
