package com.mrm.Ecommerce.Service;

import com.mrm.Ecommerce.DTO.OrderDto;
import com.mrm.Ecommerce.DTO.OrderItemsDto;
import com.mrm.Ecommerce.Entity.OrderItem;
import com.mrm.Ecommerce.Entity.Orders;
import com.mrm.Ecommerce.Entity.Product;
import com.mrm.Ecommerce.Entity.User;
import com.mrm.Ecommerce.Repository.OrderRepo;
import com.mrm.Ecommerce.Repository.ProductRepo;
import com.mrm.Ecommerce.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepo productRepo;

    public OrderDto placeOrder(Long userId, Map<Long, Integer> productQuantities, Double totalAmount) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Orders orders = new Orders();
        orders.setUser(user);
        orders.setStatus("PENDING");
        orders.setTotalAmount(totalAmount);
        orders.setPurchaseDate(new Date());


        List<OrderItem> orderItems = new ArrayList<>();
        List<OrderItemsDto> orderItemsDtos = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
            Product product = productRepo.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(orders);
            orderItem.setProduct(product);
            orderItem.setQuantity(entry.getValue());
            orderItems.add(orderItem);
            orderItemsDtos.add(new OrderItemsDto(product.getName(), product.getAmount(), entry.getValue()));

        }

        orders.setOrderItem(orderItems);
        Orders saveOrder = orderRepo.save(orders);
        return new OrderDto(saveOrder.getId(), saveOrder.getTotalAmount(), saveOrder.getStatus(), saveOrder.getPurchaseDate(), orderItemsDtos);
    }


    public List<OrderDto> getAllOrders() {
        List<Orders> orders = orderRepo.findAllOrdersWithUser();

        return orders.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private OrderDto convertToDto(Orders orders) {
        List<OrderItemsDto> orderItems = orders.getOrderItem().stream().map(
                item-> new OrderItemsDto(
                        item.getProduct().getName(),
                        item.getProduct().getAmount(),
                        item.getQuantity())).collect(Collectors.toList());
        return new OrderDto(
                orders.getId(),
                orders.getTotalAmount(),
                orders.getStatus(),
                orders.getPurchaseDate(),
                orders.getUser()!=null ? orders.getUser().getName() : "Unknown",
                orders.getUser()!=null ? orders.getUser().getEmail() : "Unknown",
                orderItems
        );
    }

    public List<OrderDto> getOrderByUser(Long userId){
        Optional<User> userOp = userRepo.findById(userId);
        if(userOp.isEmpty()){
            throw new RuntimeException("User not found");
        }
        User user=userOp.get();
        List<Orders> ordersList = orderRepo.findByUser(user);
        return ordersList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}

