package com.hexagonal.domain.order.ports;

import com.hexagonal.domain.order.model.Order;
import com.hexagonal.domain.order.model.OrderQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {

  Page<Order> findAll(OrderQuery query, Pageable pageable);

}
