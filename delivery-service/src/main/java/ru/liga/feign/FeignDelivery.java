package ru.liga.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.liga.dto.request.RequestOrderStatus;


@FeignClient(name = "order-service", url = "http://localhost:8081")
public interface FeignDelivery {

    @PutMapping("/order/{id}")
    ResponseEntity<?> updateOrderStatus( @RequestBody RequestOrderStatus requestOrderStatus
            , @PathVariable(name = "id") long id);
}

