package ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList{
    private List start;
    private List end;
    private Integer count;

    public TransactionsLinkedList() {
        this.start = new List(null);
        this.end = new List(null);

        start.next = end;
        end.prev = start;
        this.count = 0;
    }


    @Override
    public void add(Transaction transaction) {
        (new List(transaction)).addNew(end);
        count++;
    }

    @Override
    public Transaction deleteById(UUID id) {
        List tmp = start.next;

        while (tmp != end) {
            if (tmp.getTransaction().getIdentifier().equals(id)) {
                tmp.cutList();
                count--;
                return tmp.getTransaction();
            }
            tmp = tmp.next;
        }
        throw new ThrowableClass("Transaction not found!");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] transactions = new Transaction[count];

        List tmp = start.next;
        for (int i = 0; i < count; i++) {
            transactions[i] = tmp.getTransaction();
            tmp = tmp.next;
        }
        return transactions;
    }

    private class List {
        private Transaction transaction;
        private List next;
        private List prev;

        public List(Transaction transaction) { this.transaction = transaction;}

        public void cutList()
        {
            next.prev = prev;
            prev.next = next;
            next = null;
            prev = null;
        }

        public void addNew(List end) {
            prev = end.prev;
            prev.next = this;
            end.prev = this;
            next = end;
        }
        public Transaction getTransaction() { return transaction;}
        public List next() { return next;}
    }
}
