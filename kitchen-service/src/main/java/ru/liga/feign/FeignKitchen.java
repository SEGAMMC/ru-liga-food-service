package ru.liga.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.response.ResponseOrderStatusByKitchen;


@FeignClient(name = "order-service", url = "http://localhost:8081/api/orders")
public interface FeignKitchen {


    @PutMapping("/updateStatusKitchen")
    public ResponseEntity<Void> updateOrderStatusByKitchen(
            @RequestBody RequestOrderStatus requestOrderStatus);


    @GetMapping("/{uuid}/kitchen")
    public ResponseOrderStatusByKitchen getOrderByUuid (
            @PathVariable(name = "uuid") String uuid);

}