package com.url.url_shortner.repository;

import com.url.url_shortner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
}
