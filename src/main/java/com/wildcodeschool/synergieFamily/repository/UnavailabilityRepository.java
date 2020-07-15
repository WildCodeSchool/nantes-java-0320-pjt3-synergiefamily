package com.wildcodeschool.synergieFamily.repository;

import com.wildcodeschool.synergieFamily.entity.Unavailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnavailabilityRepository extends JpaRepository<Unavailability, Long> {
}
