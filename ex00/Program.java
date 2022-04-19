package ex00;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Rec", 1000);
        user1.setIdentifier(1);
        User user2 = new User("Send", 10000);
        user2.setIdentifier(2);
        TransferCategory out = new TransferCategory("credit");
        TransferCategory in = new TransferCategory("debit");


        Transaction trans1 = Transaction.doTransaction(user2, user1, out, -1000);
        System.out.println(trans1);
        System.out.println("Sender balance= " + user2.getBalance());
        System.out.println("Recipient balance= " + user1.getBalance());
        System.out.println("-----------------------------------");
        Transaction trans2 = Transaction.doTransaction(user1, user2, in, 999);
        System.out.println(trans2);
        System.out.println("Sender balance= " + user2.getBalance());
        System.out.println("Recipient balance= " + user1.getBalance());
        System.out.println("-----------------------------------");
        TransferCategory bad = new TransferCategory("asdasdasd");
    }
}
