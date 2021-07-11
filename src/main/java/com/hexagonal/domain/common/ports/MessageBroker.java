package com.hexagonal.domain.common.ports;

import com.hexagonal.domain.order.model.Order;

public interface MessageBroker {

  void publish(Order order);
}
