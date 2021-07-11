package com.hexagonal.adapters.api;

import com.hexagonal.adapters.api.model.CreateOrderRequestDto;
import com.hexagonal.adapters.api.model.ListOrderResponseDto;
import com.hexagonal.adapters.api.model.OrderDto;
import com.hexagonal.adapters.api.model.OrderStatusDto;
import com.hexagonal.domain.order.ports.OrderService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderManagementApiDelegateImpl implements OrderManagementApiDelegate {

  private final OrderService orderService;

  @Override
  public ResponseEntity<OrderDto> createOrder(CreateOrderRequestDto createOrderRequestDto) {
    OrderDto order = orderService.createOrder(createOrderRequestDto);
    return ResponseEntity.ok(order);
  }

  @Override
  public ResponseEntity<OrderDto> getOrder(UUID orderId) {
    OrderDto order = orderService.getOrder(orderId);
    return ResponseEntity.ok(order);
  }

  @Override
  public ResponseEntity<ListOrderResponseDto> listOrders(Integer size, Integer page,
      OrderStatusDto status) {
    ListOrderResponseDto orders = orderService.listOrders(size, page, status);
    return ResponseEntity.ok(orders);
  }
}
