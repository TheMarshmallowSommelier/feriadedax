package com.feriadedaxecommerce.controller;

import com.feriadedaxecommerce.entity.Orders;
import com.feriadedaxecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;


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
        return orderRepository.save(order);
    }

    @PutMapping("/{id}")
    public Orders updateOrder(@PathVariable Integer id, @RequestBody Orders orderDetails) {
        Orders order = orderRepository.findById(id).orElse(null);

        if (order == null) {
            return null;
        }

        order.setCustomer(orderDetails.getCustomer());
        order.setProduct(orderDetails.getProduct());
        order.setTicket(orderDetails.getTicket());
        order.setOrderDate(orderDetails.getOrderDate());
        order.setTotalPrice(orderDetails.getTotalPrice());
        order.setQuantity(orderDetails.getQuantity());

        return orderRepository.save(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderRepository.deleteById(id);
    }
}
