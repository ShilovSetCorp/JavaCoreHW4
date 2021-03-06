package com.roman.shilov.hw12.travelagency.order.service;

import com.roman.shilov.hw12.travelagency.common.solutions.service.BaseService;
import com.roman.shilov.hw12.travelagency.order.domain.Order;
import com.roman.shilov.hw12.travelagency.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {

    List<Order> search(OrderSearchCondition searchCondition);
}
