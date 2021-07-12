package com.hexagonal.adapters.commandhandler;

import com.hexagonal.domain.order.ports.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandHandler {

  private final OrderService orderService;

  public void execute(Command command) {
    orderService.prepare(command.getOrderId());
  }
}
