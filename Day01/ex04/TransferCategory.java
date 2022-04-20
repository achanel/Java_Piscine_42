package ex04;

public class TransferCategory {
    private String type;

    public TransferCategory(String type) {
        if (type.equals("credit") || type.equals("debit")) {
            this.type = type;
        }
        else
            System.err.println("Wrong type!");
    }

    public String getType() {
        return type;
    }
}
