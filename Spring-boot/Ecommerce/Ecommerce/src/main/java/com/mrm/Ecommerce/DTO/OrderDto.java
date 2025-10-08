package com.mrm.Ecommerce.DTO;

import java.util.Date;
import java.util.List;

public class OrderDto {

    private Long id;

    private Double totalAmount;

    private String status;

    private Date orderDate;

    private String username;

    private String email;

    private List<OrderItemsDto> orderItems;

    public OrderDto(Long id, Double totalAmount, String status, Date orderDate, String username, String email, List<OrderItemsDto> orderItems) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
        this.username = username;
        this.email = email;
        this.orderItems = orderItems;
    }

    public OrderDto(Long id, Double totalAmount, String status, Date orderDate, List<OrderItemsDto> orderItems) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OrderItemsDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemsDto> orderItems) {
        this.orderItems = orderItems;
    }
}
