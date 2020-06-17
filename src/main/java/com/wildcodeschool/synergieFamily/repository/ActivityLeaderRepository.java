package com.wildcodeschool.synergieFamily.repository;

import com.wildcodeschool.synergieFamily.entity.ActivityLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityLeaderRepository extends JpaRepository<ActivityLeader, Long> {

    List<ActivityLeader> findByLastNameContainingOrFirstNameContainingOrEmailContaining(String lastName, String firstName, String email);
}
