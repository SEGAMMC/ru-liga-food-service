package ru.liga.dto;


import lombok.*;
import ru.liga.entity.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseOrder {
    private long id;
    private ResponseRestaurant restaurant;
    private LocalDateTime timestamp;
    private List<ResponseItem> items;

    public ResponseRestaurant setRestaurantByOrder(Restaurant restaurant) {
        this.restaurant = new ResponseRestaurant(restaurant.getName());
        return this.restaurant;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class ResponseRestaurant {
        private String name;
    }


}
