package ru.liga.dto.response;

import lombok.*;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseCustomerProfile {

	private String phone;

	private String email;

	private String address;
	
}
