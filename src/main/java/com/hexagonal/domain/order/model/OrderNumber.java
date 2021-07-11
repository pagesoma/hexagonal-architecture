package com.hexagonal.domain.order.model;

import java.util.Objects;

public class OrderNumber {

  private String number;

  public String getNumber() {
    return number;
  }

  public OrderNumber(String number) {
    this.number = number;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof OrderNumber)) {
      return false;
    }

    OrderNumber that = (OrderNumber) o;
    return Objects.equals(number, that.number);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number);
  }

  public static OrderNumber from(String orderNumber) {
    return new OrderNumber(orderNumber);
  }
}