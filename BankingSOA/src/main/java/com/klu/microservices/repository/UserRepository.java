
package com.klu.microservices.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.microservices.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
