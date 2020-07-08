package com.wildcodeschool.synergieFamily.repository;

import com.wildcodeschool.synergieFamily.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u ORDER BY u.id DESC")
    public List<User> findAll();
}
