package ru.liga.service;

import org.springframework.stereotype.Service;
import ru.liga.controllers.request.RequestOrder;
import ru.liga.controllers.response.ResponseItem;
import ru.liga.controllers.response.ResponseOrder;
import ru.liga.controllers.response.ResponseOrderAccept;
import ru.liga.controllers.response.ResponseOrdersList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {


    public ResponseOrdersList getOrders() {
        //пример с имеющимися заказами
        ResponseOrder responseOrder1 = new ResponseOrder();
        responseOrder1.setRestraunt(new ResponseOrder.ResponseRestraunt("ShAto"));
        responseOrder1.setId(1l);
        responseOrder1.setTimestamp(LocalDateTime.now());

        List<ResponseItem> list = new ArrayList<>();
        ResponseItem ri1 = new ResponseItem(1000, 2, "Opisanie", "Pictures");
        ResponseItem ri2 = new ResponseItem(1500, 4, "Opisanie", "Pictures");
        list.add(ri1);
        list.add(ri2);
        responseOrder1.setItems(list);

        ResponseOrdersList responseOrdersList = new ResponseOrdersList();
        List<ResponseOrder> listOrder = new ArrayList<>();
        listOrder.add(responseOrder1);
        responseOrdersList.setOrders(listOrder);

        //если заказов нет вывести responseOrdersList2
        ResponseOrdersList responseOrdersList2 = new ResponseOrdersList();
        responseOrdersList2.setOrders(new ArrayList<>());
        return responseOrdersList;
    }

    public ResponseOrder getOrderById(long id) {
        return null;
    }

    public ResponseOrderAccept createNewOrder(RequestOrder requestOrder) {
        return null;
    }

    public ResponseOrder updateOrder(RequestOrder requestOrder) {
        return null;
    }

    public ResponseOrder deleteOrderById(String id) {
        return null;
    }
}
