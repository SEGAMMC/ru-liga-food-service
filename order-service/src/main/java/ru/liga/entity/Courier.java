package ru.liga.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.liga.enums.CourierStatus;

import javax.persistence.*;

//Курьеры
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "couriers")
public class Courier {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CourierStatus status;

    @Column(name = "coordinates")
    private  String coordinates;

}
