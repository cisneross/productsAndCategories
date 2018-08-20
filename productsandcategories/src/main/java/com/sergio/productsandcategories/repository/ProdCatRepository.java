package com.sergio.productsandcategories.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sergio.productsandcategories.models.CategoryProduct;

@Repository
public interface ProdCatRepository extends CrudRepository<CategoryProduct, Long>{

}
