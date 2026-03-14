package com.miniks.shop.service;

import com.miniks.shop.entity.OrderItem;

public interface OrderItemService {

    OrderItem findOrderItemById(Long id) throws Exception;

}
