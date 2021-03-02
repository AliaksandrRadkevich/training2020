package by.training.hotel.model.service.impl;

import by.training.hotel.model.transaction.Transaction;

public class BaseService {
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
