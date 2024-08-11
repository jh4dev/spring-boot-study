package com.springboot.relation.data.repository.support;

import com.springboot.relation.data.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    //Custom 메서드 작성
    List<Product> findByName(String name);

}
