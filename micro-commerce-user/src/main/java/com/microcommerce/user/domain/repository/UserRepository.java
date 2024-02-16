package com.microcommerce.user.domain.repository;

import com.microcommerce.user.domain.entity.User;
import com.microcommerce.user.domain.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailAndType(String email, UserType type);

}
