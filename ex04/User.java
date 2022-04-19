package ex04;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance;
    private TransactionsList list;

    public User(String name, Integer balance) {
        this.name = name;
        if (balance > 0)
            this.balance = balance;
        else this.balance = 0;
        identifier = UserIdsGenerator.getInstance().generateId();
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) { this.balance = balance;}

    public Integer getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTransactionsList(TransactionsList list) { this.list = list;}

    @Override
    public String toString() {
        return "User{" +
                "ID=" + identifier +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
