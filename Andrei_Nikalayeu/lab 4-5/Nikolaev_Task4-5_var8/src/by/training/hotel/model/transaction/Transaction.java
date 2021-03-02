package by.training.hotel.model.transaction;

public interface Transaction {
    void start() throws TransactionException;
    void commit() throws TransactionException;
    void rollback() throws TransactionException;
}
