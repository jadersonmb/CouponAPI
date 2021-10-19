package com.zuka.coupon.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "coupon")
public class Coupon {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "desc", columnDefinition = "json", nullable = false)
    @Convert(converter = JpaConverterJson.class)
    private Map<String, String> desc;
}
