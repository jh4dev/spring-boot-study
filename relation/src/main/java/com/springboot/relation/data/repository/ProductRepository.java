package com.springboot.relation.data.repository;

import com.springboot.relation.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /*
    * 주제 키워드 (Subject Keyword)
    * */
    // 조회 키워드를 활용한 쿼리 메서드
    Optional<Product> findByNumber(Long number);
    List<Product> findAllByName(String name);
    Product queryByNumber(Long number);

    // 특정 데이터가 존재하는지 확인하는 키워드
    boolean existsByNumber(Long number);

    // 조회 결과의 Row 수를 리턴하는 키워드
    long countByName(String name);

    // 삭제 키워드
    long deleteByNumber(long number); // 삭제된 Row 수 리턴
    void removeByNumber(long number); // void 로도 처리 가능

    // First, Top
    List<Product> findFirst5ByName(String name);
    List<Product> findTop5ByName(String name);

    /*
    * 조건자 키워드 (Predicate Keyword)
    * */
    // Is, Equals > 일치하는 데이터를 찾는다.
    Product findByNumberIs(Long number);
    Product findByNumberEquals(Long number);

    // (Is)Not > 불일치하는 데이터를 찾는다.
    Product findByNumberIsNot(Long number);
    Product findByNumberNot(Long number);

    // (Is)Null, (Is)NotNull
    List<Product> findByUpdatedAtNull();
    List<Product> findByUpdatedAtIsNull();
    List<Product> findByUpdatedAtNotNull();
    List<Product> findByUpdatedAtIsNotNull();

    // (Is)True, (Is)False
    Product findByIsActiveTrue();
    Product findByIsActiveIsTrue();
    Product findByIsActiveFalse();
    Product findByIsActiveIsFalse();

    // And, Or > 조건을 묶을 때 사용
    Product findByNumberAndName(Long number, String nme);
    Product findByNumberOrName(Long number, String name);

    // (Is)GreaterThan, (Is)LessThan, (Is)Between + Equal
    // GreaterThan
    List<Product> findByPriceIsGreaterThan(Long price);     // Price 초과
    List<Product> findByPriceGreaterThan(Long price);       // Price 초과
    List<Product> findByPriceGreaterThanEqual(Long Price);  // Price 이상
    // LessThan
    List<Product> findByPriceIsLessThan(Long price);        // Price 미만
    List<Product> findByPriceLessThan(Long price);          // Price 미만
    List<Product> findByPriceLessThanEqual(Long Price);     // Price 이하
    // Between
    List<Product> findByPriceBetween(Long lowPrice, Long highPrice);
    List<Product> findByPriceIsBetween(Long lowPrice, Long highPrice);

    // (Is)StartsWith / StartingWith, (Is)EndsWith / EndingWith, (Is)Contains / Containing, (Is)Like
    // StartsWith / StartingWith
    List<Product> findByNameStartsWith(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameIsStartingWith(String name);

    // EndsWith / EndingWith
    List<Product> findByNameEndsWith(String name);
    List<Product> findByNameEndingWith(String name);
    List<Product> findByNameIsEndingWith(String name);

    // Contains / Containing
    List<Product> findByNameContains(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameIsContaining(String name);

    // Like > 파라미터의 원하는 위치에 '%' 를 입력하여 호출해야 함
    List<Product> findByNameLike(String name);
    List<Product> findByNameIsLike(String name);


    /*
    * 정렬 & 페이징
    * */
    // 정렬
    List<Product> findByNameOrderByNumberAsc(String name);
    List<Product> findByNameOrderByNumberDesc(String name);
    List<Product> findByNameOrderByPriceAscStockDesc(String name);  // 정렬 컬럼 간 And 연결하지 않고 나열
    List<Product> findByName(String name, Sort sort);

    // 페이징
    Page<Product> findByName(String name, Pageable pageable);

    // @Query 어노테이션 사용
    @Query("select p from Product as p where p.name = ?1")
    List<Product> findByName(String name);
    @Query("select p from Product p where p.name = :name")
    List<Product> findBtyNameParam(@Param("name") String name);

    @Query("select p.name, p.price, p.stock from Product p where p.name = :name")
    List<Object[]> findByNameParam2(@Param("name") String name);
}
