package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.github.mstachniuk.graphqljavaexample.order.Order;
import io.github.mstachniuk.graphqljavaexample.order.OrderService;
import io.github.mstachniuk.graphqljavaexample.order.Status;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpqrOrderQuery {

    @Autowired
    private OrderService orderService;

    @GraphQLQuery(name = "orders")
    public List<SpqrOrder> getOrdersForCustomer(@GraphQLContext SpqrCustomer customer) {
        return toSpqr(orderService.getOrdersByCustomerId(customer.getId()));
    }

    private List<SpqrOrder> toSpqr(List<Order> orders) {
        return orders.stream()
                .map(order -> new SpqrOrder(order.getId(), mapStatus(order.getStatus())))
                .collect(Collectors.toList());
    }

    private SpqrStatus mapStatus(Status status) {
        return SpqrStatus.valueOf(status.name());
    }
}
