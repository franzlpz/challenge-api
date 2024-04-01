package com.franzlopez.apirestfull.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "phones")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Phone {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String number;
    @Column(name = "city_code")
    private String cityCode;
    @Column(name = "country_code")
    private String countryCode;
    @ManyToOne(fetch = FetchType.LAZY)
    public User user;
}
