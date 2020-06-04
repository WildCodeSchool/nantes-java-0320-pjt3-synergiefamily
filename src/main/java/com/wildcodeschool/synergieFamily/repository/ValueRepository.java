package com.wildcodeschool.synergieFamily.repository;

import com.wildcodeschool.synergieFamily.entity.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValueRepository extends JpaRepository<Value, Long> {}