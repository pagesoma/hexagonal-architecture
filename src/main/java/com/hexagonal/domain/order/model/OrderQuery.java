package com.hexagonal.domain.order.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderQuery {

  private OrderStatus orderStatus;

  public OrderQuery() {
  }

  public OrderQuery(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }
}