package com.springboot.advjpa.data.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.advjpa.data.entity.Product;
import com.springboot.advjpa.data.entity.QProduct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class QueryDslTest extends RepositoryTestBase{

    @Autowired
    ProductRepository productRepository;


    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    void queryDslTest01(){

        JPAQuery<Product> query = new JPAQuery<Product>(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query.from(qProduct).where(qProduct.name.eq("펜")).orderBy(qProduct.price.asc()).fetch();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==============================");
        for(Product product : productList) {
            stringBuilder.append("---------------------------- \n")
                    .append("Product Number : ").append(product.getNumber()).append("\n")
                    .append("Product Name : ").append(product.getName()).append("\n")
                    .append("Product Price : ").append(product.getPrice()).append("\n")
                    .append("Product Stock : ").append(product.getStock()).append("\n")
                    .append("---------------------------- \n");
        }
        log.info(stringBuilder.toString());
    }

    @Test
    void queryDslTest02() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = jpaQueryFactory.selectFrom(qProduct)    //select 절부터 작성한다.ㅇ
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n==============================\n");
        for(Product product : productList) {
            stringBuilder.append("---------------------------- \n")
                    .append("Product Number : ").append(product.getNumber()).append("\n")
                    .append("Product Name : ").append(product.getName()).append("\n")
                    .append("Product Price : ").append(product.getPrice()).append("\n")
                    .append("Product Stock : ").append(product.getStock()).append("\n")
                    .append("---------------------------- \n");
        }
        log.info(stringBuilder.toString());
    }
    @Autowired
    EntityManager entityManager;
    @Test
    void queryDslTest03() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        // Single select
        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n==============================\n");
        for(String product : productList) {
            stringBuilder.append("---------------------------- \n")
                    .append("Product Name : ").append(product).append("\n")
                    .append("---------------------------- \n");
        }
        log.info(stringBuilder.toString());

        // List Select
        List<Tuple> tupleList = jpaQueryFactory
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        stringBuilder.setLength(0);
        stringBuilder.append("\n==============================\n");
        for(Tuple tuple : tupleList) {
            stringBuilder.append("---------------------------- \n")
                    .append("Product Name : ").append(tuple.get(qProduct.name)).append("\n")
                    .append("Product Price : ").append(tuple.get(qProduct.price)).append("\n")
                    .append("---------------------------- \n");
        }
        log.info(stringBuilder.toString());

    }

    @Test
    void queryDslTest04() {
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n==============================\n");
        for(String product : productList) {
            stringBuilder.append("---------------------------- \n")
                    .append("Product Name : ").append(product).append("\n")
                    .append("---------------------------- \n");
        }
        log.info(stringBuilder.toString());
    }

}
