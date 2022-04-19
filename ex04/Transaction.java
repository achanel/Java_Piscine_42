package ex04;

import java.util.UUID;

public class Transaction {

    private UUID identifier;
    private User recipient;
    private User sender;
    private TransferCategory category;
    private Integer transferAmount;

    public Transaction(User recipient, User sender, TransferCategory category, Integer transferAmount) {
        this.identifier = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.category = category;
        this.transferAmount = transferAmount;
    }

    public static Transaction doTransaction(User u1, User u2, TransferCategory category, Integer transferAmount) {
        if ((category.getType().equals("credit") && transferAmount < 0 && u2.getBalance() >= -transferAmount)
            || (category.getType().equals("debit") && transferAmount > 0 && u1.getBalance() >= transferAmount)) {
            if ((category.getType().equals("credit"))) {
                u2.setBalance(u2.getBalance() - transferAmount);
                u1.setBalance(u1.getBalance() + transferAmount);
            }
            else if ((category.getType().equals("debit"))) {
                u1.setBalance(u1.getBalance() - transferAmount);
                u2.setBalance(u2.getBalance() + transferAmount);
            }
            return new Transaction(u1, u2, category, transferAmount);
        }
        System.err.println("Wrong transfer Amount!");
        return null;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "ID=" + identifier +
                ", Recipient=" + recipient.getName() +
                ", Sender=" + sender.getName() +
                ", Category=" + category.getType() +
                ", TransferAmount=" + transferAmount +
                '}';
    }
}
