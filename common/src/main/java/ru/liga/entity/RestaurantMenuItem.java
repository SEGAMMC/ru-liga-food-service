package ru.liga.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//блюда в ресторане
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "restaurant_menu_items")
public class RestaurantMenuItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurantId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;
}
