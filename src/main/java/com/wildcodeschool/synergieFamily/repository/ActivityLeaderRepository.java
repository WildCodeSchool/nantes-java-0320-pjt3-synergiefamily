package com.wildcodeschool.synergieFamily.repository;

import com.wildcodeschool.synergieFamily.entity.ActivityLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLeaderRepository extends JpaRepository<ActivityLeader, Long> {

    public List<ActivityLeader> findAllByLastNameContainingAndFirstNameContaining(String lastName, String firstName);


}
