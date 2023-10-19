package ru.liga.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseOrdersList {
    private List<ResponseOrder> orders;
    private int page_index;
    private int page_count;

}
