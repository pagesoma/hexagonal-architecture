package com.hexagonal.domain.order.model.converter;

import com.hexagonal.domain.order.model.OrderNumber;
import javax.persistence.AttributeConverter;

public class OrderNumberConverter implements AttributeConverter<OrderNumber, String> {

  @Override
  public String convertToDatabaseColumn(OrderNumber attribute) {
    return attribute.getNumber();
  }

  @Override
  public OrderNumber convertToEntityAttribute(String dbData) {
    return OrderNumber.from(dbData);
  }
}
