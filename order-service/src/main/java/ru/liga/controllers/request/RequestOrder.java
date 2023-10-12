package ru.liga.controllers.request;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestOrder {
    private long restaurant_id;
    private List<RequestItemsList> menu_items;

    @Setter
    @Getter
    @ToString
    public static class RequestItemsList {
        private int quantity;
        private long menu_item_id;
    }
}
