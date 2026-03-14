package com.miniks.shop.service.implement;

import com.miniks.shop.entity.OrderItem;
import com.miniks.shop.repository.OrderItemRepository;
import com.miniks.shop.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional(readOnly = true)
    public OrderItem findOrderItemById(Long id) throws Exception {

        return orderItemRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Order item is not existed..."));
    }

}
