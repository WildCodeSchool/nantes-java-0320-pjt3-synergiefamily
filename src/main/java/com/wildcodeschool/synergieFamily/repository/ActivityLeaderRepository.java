package com.wildcodeschool.synergieFamily.repository;

import com.wildcodeschool.synergieFamily.entity.ActivityLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ActivityLeaderRepository extends JpaRepository<ActivityLeader, Long> {

    @Query("SELECT a FROM ActivityLeader a WHERE a.lastName= :lastName OR a.firstName= :firstName OR a.email= :email ORDER BY a.lastName asc, a.firstName asc, a.email asc")
    List<ActivityLeader> findByLastNameContainingOrFirstNameContainingOrEmailContaining(@Param("lastName") String lastName , @Param("firstName") String firstName, @Param("email") String email);

    @Query("SELECT a FROM ActivityLeader a ORDER BY a.id DESC")
    public List<ActivityLeader> findAll();

}
