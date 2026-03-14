package com.miniks.shop.service.implement;

import com.miniks.shop.entity.Order;
import com.miniks.shop.entity.Seller;
import com.miniks.shop.entity.Transaction;
import com.miniks.shop.repository.SellerRepository;
import com.miniks.shop.repository.TransactionRepository;
import com.miniks.shop.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final SellerRepository sellerRepository;

    @Override
    public Transaction createTransaction(Order order) {

        Seller seller = sellerRepository.findById(order.getSellerId()).orElseThrow(
                () -> new RuntimeException("Seller not found with id: " + order.getSellerId())
        );

        Transaction transaction = new Transaction();

        transaction.setSeller(seller);
        transaction.setOrder(order);
        transaction.setCustomer(order.getUser());

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionBySellerId(Seller seller) {

        return transactionRepository.findBySellerId(seller.getId());
    }

    @Override
    public List<Transaction> getAllTransactions() {

        return transactionRepository.findAll();
    }
}
