package ru.liga.controllers.request;

import java.util.List;

public class RequestOrder {
    private long restraunt_id;
    private List<RequestItemsList> menu_items;


    private class RequestItemsList {
        private int quantity;
        private long menu_item_id;
    }
}
