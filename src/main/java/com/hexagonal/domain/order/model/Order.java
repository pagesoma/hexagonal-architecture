package com.hexagonal.domain.order.model;

import com.hexagonal.domain.order.model.converter.OrderNumberConverter;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Type;

@Getter
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Type(type = "uuid-char")
  @Column(length = 45)
  private UUID externalId;

  @Convert(converter = OrderNumberConverter.class)
  @Column(name = "order_number")
  private OrderNumber orderNumber;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  public void submit() {
    this.status = OrderStatus.SUBMITTED;
  }

  public void prepare() {
    this.status = OrderStatus.READY;
  }

  public void complete() {
    this.status = OrderStatus.COMPLETED;
  }

  public boolean cancel() {
    if (this.status != OrderStatus.COMPLETED) {
      this.status = OrderStatus.CANCELLED;
      return true;
    } else {
      return false;
    }
  }
}