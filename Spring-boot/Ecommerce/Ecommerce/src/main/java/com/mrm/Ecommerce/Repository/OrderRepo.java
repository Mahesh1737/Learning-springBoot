package com.mrm.Ecommerce.Repository;

import com.mrm.Ecommerce.Entity.Orders;
import com.mrm.Ecommerce.Entity.Product;
import com.mrm.Ecommerce.Entity.User;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders, Long> {

    @Query("SELECT o from Orders o JOIN FETCH o.user")
    List<Orders> findAllOrdersWithUser();

    List<Orders> findByUser(User user);

}
