package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.entity.Order;

import java.util.List;

public interface OrdersRepository
        extends JpaRepository<Order, Long> {
//    Subscription findBySubscriptionId(String subscriptionId);
//
//    List<Subscription> findSubscriptionByStatus(SubscriptionStatus status);
//
//    boolean existsByTransport_endpoint(String endpoint);
}
