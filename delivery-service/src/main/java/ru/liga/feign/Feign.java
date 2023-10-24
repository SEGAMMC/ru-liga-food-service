package ru.liga.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.liga.entity.Order;

@FeignClient(name = "orderClient", url = "http://localhost:9001")
public interface Feign {
    @GetMapping("/order/{id}")
    ResponseEntity<Order> getOrderById(@PathVariable("id") Long id);

}

