package ru.liga.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseOrderAccept {
    private long ig;
    private String secret_payment_url;
    private Date estimated_time_of_arrival;

}
