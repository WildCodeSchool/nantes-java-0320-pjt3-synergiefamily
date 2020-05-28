package com.wildcodeschool.synergieFamily.repository;

import com.wildcodeschool.synergieFamily.entity.Audience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudienceRepository extends JpaRepository<Audience, Long> {}
