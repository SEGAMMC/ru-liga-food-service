package ru.liga.controllers.response;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ResponseOrder {
    private long id;
    private ResponseRestraunt restraunt;
    private LocalDateTime timestamp;
    private List<ResponseItem> items;

    @Setter
    @Getter
    @AllArgsConstructor
    public static class ResponseRestraunt {
        private String name;
    }


}
