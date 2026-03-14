package com.miniks.shop.service;

import com.miniks.shop.entity.Order;
import com.miniks.shop.entity.Seller;
import com.miniks.shop.entity.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Order order);

    List<Transaction> getTransactionBySellerId(Seller seller);

    List<Transaction> getAllTransactions();

}
