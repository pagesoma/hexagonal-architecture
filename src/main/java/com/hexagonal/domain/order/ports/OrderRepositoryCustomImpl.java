package com.hexagonal.domain.order.ports;

import static com.hexagonal.domain.order.model.QOrder.order;

import com.hexagonal.domain.order.model.Order;
import com.hexagonal.domain.order.model.OrderQuery;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class OrderRepositoryCustomImpl extends QuerydslRepositorySupport
    implements OrderRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  public OrderRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
    super(Order.class);
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public Page<Order> findAll(OrderQuery query, Pageable pageable) {

    BooleanBuilder builder = new BooleanBuilder();
    if (query.getOrderStatus() != null) {
      builder.and(order.status.eq(query.getOrderStatus()));
    }

    QueryResults<Order> results =
        jpaQueryFactory
            .selectFrom(order)
            .where(builder)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(order.id.desc())
            .fetchResults();

    return results.isEmpty()
        ? Page.empty()
        : new PageImpl<>(results.getResults(), pageable, results.getTotal());
  }
}
