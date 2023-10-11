package ru.liga.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//рестораны
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;
}
