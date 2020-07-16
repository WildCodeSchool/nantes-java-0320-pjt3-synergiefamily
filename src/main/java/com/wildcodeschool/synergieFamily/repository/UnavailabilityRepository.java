package com.wildcodeschool.synergieFamily.repository;

import com.wildcodeschool.synergieFamily.entity.ActivityLeader;
import com.wildcodeschool.synergieFamily.entity.Unavailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UnavailabilityRepository extends JpaRepository<Unavailability, Long> {

    @Transactional
    @Modifying
    void deleteAllByActivityLeader(ActivityLeader activityLeader);
}
