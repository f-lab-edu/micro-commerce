package com.microcommerce.user.domain.repository;

import com.microcommerce.user.domain.entity.User;
import com.microcommerce.user.domain.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailAndType(String email, UserType type);

    Optional<User> findByEmailAndType(String email, UserType type);

}
