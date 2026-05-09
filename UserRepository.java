package com.ws101.FulgarLim.repository;

import com.ws101.FulgarLim.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}