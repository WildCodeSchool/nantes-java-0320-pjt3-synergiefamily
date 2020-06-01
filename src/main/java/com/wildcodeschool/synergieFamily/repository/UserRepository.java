package com.wildcodeschool.synergieFamily.repository;

import com.wildcodeschool.synergieFamily.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
