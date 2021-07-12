package com.hexagonal.adapters.commandhandler;

import com.hexagonal.domain.order.model.Order;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Command {
  private UUID orderId;
}
