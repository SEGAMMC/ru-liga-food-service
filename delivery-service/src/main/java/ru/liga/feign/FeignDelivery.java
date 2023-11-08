package ru.liga.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.response.ResponseDeliveryOrderForFindCourier;
import ru.liga.dto.response.ResponseOrderStatusByDelivery;

import java.util.List;


@FeignClient(name = "order-service", url = "http://localhost:8081/api/orders")
public interface FeignDelivery {

    @PutMapping("/updateStatusDelivery")
    public ResponseEntity<Void> updateOrderStatusByDelivery(
            @RequestBody RequestOrderStatus requestOrderStatus);

    @GetMapping("/deliveries")
    public List<ResponseDeliveryOrderForFindCourier> getOrdersByStatusDeliveryPending();

    @GetMapping("/{uuid}/delivery")
    public ResponseOrderStatusByDelivery getOrderByUuid (
            @PathVariable(name = "uuid") String uuid);

}

