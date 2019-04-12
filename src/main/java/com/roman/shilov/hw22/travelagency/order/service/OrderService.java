package com.roman.shilov.hw22.travelagency.order.service;

import com.roman.shilov.hw22.travelagency.common.solutions.service.BaseService;
import com.roman.shilov.hw22.travelagency.order.domain.Order;
import com.roman.shilov.hw22.travelagency.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {

    List<Order> search(OrderSearchCondition searchCondition);
}
