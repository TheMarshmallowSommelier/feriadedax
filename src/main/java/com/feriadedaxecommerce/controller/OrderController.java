package com.feriadedaxecommerce.controller;

import com.feriadedaxecommerce.entity.OrderItem;
import com.feriadedaxecommerce.entity.Orders;
import com.feriadedaxecommerce.repository.OrderItemRepository;
import com.feriadedaxecommerce.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping("/")
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Orders getOrderById(@PathVariable Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Orders createOrder(@RequestBody Orders order) {
        Orders savedOrder = orderRepository.save(order);

        // Save order items
        for (OrderItem item : order.getOrderItems()) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }

        return savedOrder;
    }

    @PutMapping("/{id}")
    public Orders updateOrder(@PathVariable Integer id, @RequestBody Orders orderDetails) {
        Orders order = orderRepository.findById(id).orElse(null);

        if (order == null) {
            return null;
        }

        order.setCustomer(orderDetails.getCustomer());
        order.setOrderDate(orderDetails.getOrderDate());
        order.setTotalPrice(orderDetails.getTotalPrice());

        // Update order items
        for (OrderItem item : order.getOrderItems()) {
            for (OrderItem itemDetails : orderDetails.getOrderItems()) {
                if (item.getId().equals(itemDetails.getId())) {
                    item.setProduct(itemDetails.getProduct());
                    item.setQuantity(itemDetails.getQuantity());
                    orderItemRepository.save(item);
                    break;
                }
            }
        }

        return orderRepository.save(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        // Delete order items
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(id);
        orderItemRepository.deleteAll(orderItems);

        // Delete order
        orderRepository.deleteById(id);
    }
}
