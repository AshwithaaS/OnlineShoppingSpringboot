package com.api.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.service.modal.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

}