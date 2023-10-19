package ru.liga.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

//Заказчики
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

//    @ManyToMany(mappedBy = "customerEntitySet")
//    private Set<Order> orders;

}
