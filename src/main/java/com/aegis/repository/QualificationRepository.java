package com.aegis.repository;

import com.aegis.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface QualificationRepository extends JpaRepository<Qualification, Long> {
}
