package com.hexagonal.domain.order.ports.mapper;

import com.hexagonal.domain.order.model.OrderNumber;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface OrderNumberMapper {

  default String map(OrderNumber orderNumber) {
    return orderNumber.toString();
  }

  default OrderNumber map(String string) {
    return OrderNumber.from(string);
  }
}
