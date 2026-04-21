package com.sawi_health.demo.repository;


import com.sawi_health.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Modifying(clearAutomatically = true)
    List<User> findByName(String name);
    //User findById(Long id);

    Optional<User> findById(Long id);
}



