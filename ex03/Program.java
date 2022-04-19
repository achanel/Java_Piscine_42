package ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("user1", 10000);
        User user2 = new User("user2", 22222);
        User user3 = new User("user3", 33333);

        TransferCategory out = new TransferCategory("credit");
        TransferCategory in = new TransferCategory("debit");

        TransactionsList list = new TransactionsLinkedList();
        user1.setTransactionsList(list);
        user2.setTransactionsList(list);
        user3.setTransactionsList(list);

        Transaction trans1 = Transaction.doTransaction(user1, user2, out, -1000);
        Transaction trans2 = Transaction.doTransaction(user2, user3, in, 4342);
        Transaction trans3 = Transaction.doTransaction(user3, user1, in, 500);
        Transaction trans4 = Transaction.doTransaction(user2, user1, in, 100);

        list.add(trans1);
        list.add(trans2);
        list.add(trans3);
        list.add(trans4);

        for (Transaction array : list.toArray()) {
            System.out.println(array);
        }
        System.out.println("---------------------------");
        System.out.println(list.deleteById(trans4.getIdentifier()));
        System.out.println("---------------------------");
        for (Transaction array : list.toArray()) {
            System.out.println(array);
        }
        System.out.println("---------------------------");
        UUID x = UUID.randomUUID();
        list.deleteById(x);
    }
}
