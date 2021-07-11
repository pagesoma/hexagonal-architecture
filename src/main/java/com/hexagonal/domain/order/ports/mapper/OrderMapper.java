package com.hexagonal.domain.order.ports.mapper;

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
public interface OrderMapper extends EntityMapper<OrderDto, Order> {

  @Mapping(target = "id", source = "externalId")
//  @Mapping(target = "orderStatus", source = "status")
  OrderDto toDto(Order entity);
}