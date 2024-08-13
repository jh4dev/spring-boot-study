package com.springboot.relation.data.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "product")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    // 1:1관계에서 양방향 외래키 설정 시, left outer join 2회 수행되어 효율성이 떨어짐
    // mappedBy 설정하여, ProductDetail 엔티티가 Product 엔티티의 주인이 되도록 설정
    @OneToOne(mappedBy = "product")
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    @ToString.Exclude
    private Provider provider;

    @ManyToMany
    @ToString.Exclude
    private List<Producer> producers = new ArrayList<>();

    @ColumnDefault("true")
    private boolean isActive;

    @PrePersist
    protected void onCreate() {
        if (!isActive) { // isActive 필드가 false 로 설정되어 있으면 true 로 변경
            isActive = true;
        }
    }
}