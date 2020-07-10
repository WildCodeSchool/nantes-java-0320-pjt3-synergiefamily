package com.wildcodeschool.synergieFamily.repository;

import com.wildcodeschool.synergieFamily.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
}
