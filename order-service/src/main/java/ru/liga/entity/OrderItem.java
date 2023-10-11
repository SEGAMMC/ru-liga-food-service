package ru.liga.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//список заказов
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "order_items")
public class OrderItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "order_id")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;

//    @Column(name = "restaurant_menu_item")
    @OneToOne
    @JoinColumn(name = "restaurant_menu_item")
    private RestaurantMenuItem restaurantMenuItem;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;
}
