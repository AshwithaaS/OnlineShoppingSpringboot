package com.api.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.service.modal.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}
