package com.springboot.advjpa.data.repository.support;

import com.springboot.advjpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findByName(String name);

}
