package com.hexagonal.domain.order.ports;

import com.hexagonal.domain.order.model.Order;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

  Order findOneByExternalId(UUID externalId);
}
