package com.hexagonal.domain.order.ports;

import com.hexagonal.adapters.api.model.CreateOrderRequestDto;
import com.hexagonal.adapters.api.model.ListOrderResponseDto;
import com.hexagonal.adapters.api.model.OrderDto;
import com.hexagonal.adapters.api.model.OrderStatusDto;
import com.hexagonal.domain.common.ports.MessageBroker;
import com.hexagonal.domain.order.model.Order;
import com.hexagonal.domain.order.model.OrderQuery;
import com.hexagonal.domain.order.model.OrderStatus;
import com.hexagonal.domain.order.ports.OrderRepository;
import com.hexagonal.domain.order.ports.mapper.CreateOrderRequestMapper;
import com.hexagonal.domain.order.ports.mapper.OrderMapper;
import com.hexagonal.web.util.PaginationUtil;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final CreateOrderRequestMapper createOrderRequestMapper;
  private final OrderMapper orderMapper;

  // DB를 위한 포트
  private final OrderRepository orderRepository;

  // 메세지 스트림 전송을 위한 포트
//  private final MessageBroker messageBroker;

  @Transactional
  public OrderDto createOrder(CreateOrderRequestDto req) {
    Order order = createOrderRequestMapper.toEntity(req);
    order.submit();
    orderRepository.save(order);
//    messageBroker.publish(order);
    return orderMapper.toDto(order);
  }

  @Transactional(readOnly = true)
  public OrderDto getOrder(UUID orderId) {
    Order order = orderRepository.findOneByExternalId(orderId);
    return orderMapper.toDto(order);
  }

  @Transactional(readOnly = true)
  public ListOrderResponseDto listOrders(Integer size, Integer page, OrderStatusDto orderStatus) {
    Pageable pageable = PaginationUtil.getPageRequest(size, page);

    OrderQuery query = new OrderQuery();
    query.setOrderStatus(OrderStatus.valueOf(orderStatus.toString()));

    Page<OrderDto> orders = orderRepository.findAll(query, pageable).map(orderMapper::toDto);

    return new ListOrderResponseDto()
        .data(orders.getContent())
        .page(PaginationUtil.getPageMetadata(orders));
  }

  @Transactional
  public void prepare(UUID orderId) {
    Order order = orderRepository.findOneByExternalId(orderId);
    if (order == null) {
      throw new EntityNotFoundException();
    }

    order.prepare();
    orderRepository.save(order);
  }

  @Transactional
  public void complete(UUID orderId) {
    Order order = orderRepository.findOneByExternalId(orderId);
    if (order == null) {
      throw new EntityNotFoundException();
    }

    order.complete();
    orderRepository.save(order);
  }

  @Transactional
  public void cancel(UUID orderId) {
    Order order = orderRepository.findOneByExternalId(orderId);
    if (order == null) {
      throw new EntityNotFoundException();
    }

    if (order.cancel()) {
      orderRepository.save(order);
    }
  }
}
