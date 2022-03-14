package com.api.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.service.modal.Product;

public interface IProductRepository extends JpaRepository<Product, Long>{

}
