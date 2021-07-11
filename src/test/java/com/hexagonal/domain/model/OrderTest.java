package com.hexagonal.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.hexagonal.domain.order.model.Order;
import com.hexagonal.domain.order.model.OrderStatus;
import org.junit.jupiter.api.Test;

//@ExtendWith(MockitoExtension.class)
class OrderTest {

  @Test
  void 주문은_등록상태를_가진다() {
    Order order = new Order();
    order.submit();
    assertThat(order.getStatus()).isEqualTo(OrderStatus.SUBMITTED);
  }

  @Test
  void 주문은_배송준비상태를_가진다() {
    Order order = new Order();
    order.prepare();
    assertThat(order.getStatus()).isEqualTo(OrderStatus.READY);
  }

  @Test
  void 주문은_배송완료상태를_가진다() {
    Order order = new Order();
    order.complete();
    assertThat(order.getStatus()).isEqualTo(OrderStatus.COMPLETED);
  }

  @Test
  void 주문은_취소상태를_가진다() {
    Order order = new Order();
    order.cancel();
    assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCELLED);
  }

  @Test
  void 주문은_배송완료후_취소할수없다() {
    Order order = new Order();
    order.complete();
    order.cancel();
    assertThat(order.getStatus()).isNotEqualTo(OrderStatus.CANCELLED);
    assertThat(order.getStatus()).isEqualTo(OrderStatus.COMPLETED);
  }
}
