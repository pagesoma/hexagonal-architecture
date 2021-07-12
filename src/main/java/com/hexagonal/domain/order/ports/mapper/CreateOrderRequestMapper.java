package com.hexagonal.domain.order.ports.mapper;

import com.hexagonal.adapters.api.model.CreateOrderRequestDto;
import com.hexagonal.adapters.api.model.OrderDto;
import com.hexagonal.domain.common.mapper.EntityMapper;
import com.hexagonal.domain.common.mapper.ExternalIdMapper;
import com.hexagonal.domain.order.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {
        ExternalIdMapper.class,
        OrderNumberMapper.class
    }
)
public interface CreateOrderRequestMapper extends EntityMapper<OrderDto, Order> {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "orderId", ignore = true)
  Order toEntity(CreateOrderRequestDto dto);
}